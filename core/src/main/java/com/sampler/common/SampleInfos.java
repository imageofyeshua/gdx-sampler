package com.sampler.common;

import com.sampler.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleInfos {

    private static final List<SampleInfo> ALL = Arrays.asList(
        AppListenerSample.SAMPLE_INFO,
        GdxGeneratedSample.SAMPLE_INFO,
        GdxModuleSample.SAMPLE_INFO,
        GdxReflectionSample.SAMPLE_INFO,
        InputListeningSample.SAMPLE_INFO,
        InputPollingSample.SAMPLE_INFO,
        OrthographicCameraSample.SAMPLE_INFO
    );

    public static List<String> getSampleNames() {
        List<String> ret = new ArrayList<String>();

        for (SampleInfo info : ALL) {
            ret.add(info.getName());
        }

        Collections.sort(ret);
        return ret;
    }

    public static SampleInfo find(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name is null or empty");
        }

        SampleInfo ret = null;

        for(SampleInfo info : ALL) {
            if (info.getName().equals(name)) {
                ret = info;
                break;
            }
        }

        if (ret == null) {
            throw new IllegalArgumentException("Sample not found: " + name);
        }

        return ret;
    }

    private SampleInfos() {}
}
