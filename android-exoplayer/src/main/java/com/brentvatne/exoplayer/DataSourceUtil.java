package com.brentvatne.exoplayer;

import android.content.Context;

import com.facebook.react.modules.network.OkHttpClientProvider;
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

public class DataSourceUtil {

    private DataSourceUtil() {
    }

    private static DataSource.Factory rawDataSourceFactory = null;
    private static DataSource.Factory defaultDataSourceFactory = null;
    private static String userAgent = null;

    public static void setUserAgent(String userAgent) {
        DataSourceUtil.userAgent = userAgent;
    }

    public static String getUserAgent(Context context) {
        if (userAgent == null) {
            userAgent = Util.getUserAgent(context.getApplicationContext(), "ReactNativeVideo");
        }
        return userAgent;
    }

    public static DataSource.Factory getRawDataSourceFactory(Context context) {
        if (rawDataSourceFactory == null) {
            rawDataSourceFactory = buildRawDataSourceFactory(context);
        }
        return rawDataSourceFactory;
    }

    public static void setRawDataSourceFactory(DataSource.Factory factory) {
        DataSourceUtil.rawDataSourceFactory = factory;
    }

    public static DataSource.Factory getDefaultDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        if (defaultDataSourceFactory == null) {
            defaultDataSourceFactory = buildDataSourceFactory(context, bandwidthMeter);
        }
        return defaultDataSourceFactory;
    }

    public static void setDefaultDataSourceFactory(DataSource.Factory factory) {
        DataSourceUtil.defaultDataSourceFactory = factory;
    }

    private static DataSource.Factory buildRawDataSourceFactory(Context context) {
        return new RawResourceDataSourceFactory(context.getApplicationContext());
    }

    private static DataSource.Factory buildDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        Context appContext = context.getApplicationContext();
        return new DefaultDataSourceFactory(appContext, bandwidthMeter,
                buildHttpDataSourceFactory(appContext, bandwidthMeter));
    }

    private static HttpDataSource.Factory buildHttpDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        OkHttpDataSourceFactory httpDataSourceFactory = new OkHttpDataSourceFactory(OkHttpClientProvider.getOkHttpClient(), getUserAgent(context), bandwidthMeter);

//            httpDataSourceFactory.getDefaultRequestProperties().set("Cookie", cooky);

        return httpDataSourceFactory;
    }

}

/**
 * Object {CloudFront-Policy: "eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9tZW…oYW4iOnsiQVdTOkVwb2NoVGltZSI6MTUyNjY2MzE1NX19fV19", CloudFront-Signature: "lCpCJGYTmd0n3kq9AoSR68XdT~02dpTSVYoZ16swsi-AfoMmct…i25UaaS7r6rnM1e9TtvhHZJDZSF5N3dfQDh67QdGt78GgkQ__", CloudFront-Key-Pair-Id: "APKAJGZNI64GDFVTOK7Q"}
 CloudFront-Key-Pair-Id
 :
 "APKAJGZNI64GDFVTOK7Q"
 CloudFront-Policy
 :
 "eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9tZWRpYS5odWIzMS5jb20vKiIsIkNvbmRpdGlvbiI6eyJEYXRlTGVzc1RoYW4iOnsiQVdTOkVwb2NoVGltZSI6MTUyNjY2MzE1NX19fV19"
 CloudFront-Signature
 :
 "lCpCJGYTmd0n3kq9AoSR68XdT~02dpTSVYoZ16swsi-AfoMmctwn49VqBLnus0akTuLMaQ1E1eP8B2wnMnztP-NPBerOqEFMnIq6IkM1z~UWp0uqjqlZpk~kS2bKwKnod3gHOJ5eeZaKO2Kyj2vP2fSbqqkcKzrPkCzAVzdF7L5ELOcjbpKg9Xbk0P1iyjJv9tzJAkp-qUlarX69QJ-tHS66Ca6fPR42sDvLkBPHbbdr1jht1wrcc91rHPMhffmi1gksuA8lTdTSIFUgVYjjPkBYIzqxVFVg5qXt7qGi25UaaS7r6rnM1e9TtvhHZJDZSF5N3dfQDh67QdGt78GgkQ__"
 __proto__
 :
 Object
 * **/