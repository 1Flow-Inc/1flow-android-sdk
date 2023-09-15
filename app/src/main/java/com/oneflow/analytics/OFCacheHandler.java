package com.oneflow.analytics;

import android.content.Context;

import com.oneflow.analytics.model.OFApiInterface;
import com.oneflow.analytics.model.OFRetroBaseService;
import com.oneflow.analytics.sdkdb.OFOneFlowSHP;
import com.oneflow.analytics.utils.OFConstants;
import com.oneflow.analytics.utils.OFHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OFCacheHandler extends Thread {
    Context context;
    String tag = this.getClass().getName();

    public OFCacheHandler(Context context) {
        this.context = context;
        OFHelper.v(tag, "1Flow CacheHandler constructor called");
    }

    @Override
    public void run() {
        super.run();
        OFHelper.v(tag, "1Flow CacheHandler started");
        downloadFileNew();
    }


    public void downloadFileNew() {

        try {

            String apiUrl = "https://cdn.1flow.app/index-dev.js";//original url
//            String apiUrl = "https://cdn-development.1flow.ai/js-sdk/filter.js";
            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the input stream containing the data
            InputStream inputStream = connection.getInputStream();

            // Create a cache file
            File cacheFile = new File(context.getCacheDir(), OFConstants.cacheFileName);

            // Create an output stream to write data to the cache file
            OutputStream outputStream = new FileOutputStream(cacheFile);

            //Read the data from the input stream and write it to the cache file
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                //OFHelper.v(tag, new String(buffer,0,bytesRead));
                outputStream.write(buffer, 0, bytesRead);

            }

            // Close the streams
            inputStream.close();
            outputStream.close();

            OFHelper.v(tag, "Data downloaded and cached successfully.");
            OFOneFlowSHP.getInstance(context).storeValue(OFConstants.SHP_CACHE_FILE_UPDATE_TIME, System.currentTimeMillis());

        } catch (IOException e) {
            OFHelper.e(tag, "Error downloading and caching data: " + e.getMessage());
        }
    }

    public void createCacheFile(String fileData) {
        OFHelper.v(tag, "1Flow CacheHandler creating cache File");
        // Get the cache directory
        File cacheDir = context.getCacheDir();

        // Create a file within the cache directory
        String fileName = OFConstants.cacheFileName;
        File cacheFile = new File(cacheDir, fileName);

        try {

            // Create the cache file
            if (cacheFile.createNewFile()) {
                OFHelper.v(tag, "1Flow CacheHandler cache file created");
                // File creation successful
                // You can perform read/write operations on the cache file here

                // Example: Write data to the cache file
                FileWriter writer = new FileWriter(cacheFile);
                writer.write(fileData);
                writer.flush();
                writer.close();
                OFHelper.v(tag, "1Flow CacheHandler cache file content written[" + cacheFile.length() + "]");
            } else {
                // File creation failed
                // Handle the error or try a different approach
                OFHelper.v(tag, "1Flow CacheHandler cache file not created");

            }
        } catch (IOException e) {
           // e.printStackTrace();
            // Handle the exception
        }
    }
}
