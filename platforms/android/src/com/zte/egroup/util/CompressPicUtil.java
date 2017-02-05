package com.zte.egroup.util;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Torick on 17/2/3.
 */

public class CompressPicUtil
{
    private static CompressPicUtil instance = new CompressPicUtil();

    private ConcurrentHashMap<String, PicInfoBean> map = new ConcurrentHashMap<String, PicInfoBean>();

    private CompressPicUtil()
    {
    }

    public static CompressPicUtil getInstance()
    {
        return instance;
    }

    public void add(final PicInfoBean bean)
    {
        if (null == bean)
        {
            return;
        }

        if (null == bean.srcPath || "".equals(bean.srcPath))
        {
            return;
        }

        PicInfoBean oldBean = map.get(bean.srcPath);
        if (null != oldBean)
        {
            oldBean.isSelected = true;
            return;
        }

        map.put(bean.srcPath, bean);
        new Thread(){
            @Override
            public void run()
            {
                // 压缩图片

                // 更新结果值

                // 异步线程处理，结束时判断下是否取消选中，若取消了则清除相应数据
                if (!bean.isSelected)
                {
                    remove(bean.srcPath);
                }
            }
        }.start();
    }

    public void remove(String key)
    {
        PicInfoBean bean = map.get(key);

        if (null == bean)
        {
            return;
        }

        bean.isSelected = false;

        File f = new File(bean.desPath);
        if (f.isFile())
        {
            f.delete();
        }

        map.remove(key);
    }

    public PicInfoBean get(String key)
    {
        return map.get(key);
    }

    public static class PicInfoBean
    {
        private String srcPath;
        private String desPath;
        private boolean isSelected = true;
        private int resultCode = -1; // -1：默认值；0-成功；999-未知异常结束

        public PicInfoBean(String srcPath)
        {
            this.srcPath = srcPath;

            String suffix = "";
            int pos = -1;
            if (null != srcPath)
            {
                pos = srcPath.lastIndexOf(".");
            }
            if (pos > 0)
            {
                suffix = srcPath.substring(pos);
            }

            this.desPath = "selected_pic_" + System.currentTimeMillis() + "." + suffix;
        }

        public String getSrcPath()
        {
            return srcPath;
        }

        public String getDesPath()
        {
            return desPath;
        }

        public Integer getResultCode()
        {
            return resultCode;
        }

        @Override
        public String toString()
        {
            return "PicInfoBean{" +
                    "srcPath='" + srcPath + '\'' +
                    ", desPath='" + desPath + '\'' +
                    ", resultCode=" + resultCode +
                    '}';
        }
    }
}
