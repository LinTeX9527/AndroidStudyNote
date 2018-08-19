package com.lintex9527.android.gamecenter;

import android.app.Application;
import android.content.res.Configuration;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.AndroidPrinter;
import com.elvishew.xlog.printer.ConsolePrinter;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.FilePrinter;
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator;

/**
 * Created by lintex9527@yeah.net on 2018/8/17 11:23
 * 自定义的类，主要在这里注册一个XLog 用来调试程序
 * https://github.com/elvishew/XLog/blob/master/README_ZH.md
 */
public class GameApplication extends Application {
    public GameApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel( BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE) // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
                .tag("MY_TAG")                                         // 指定 TAG，默认为 "X-LOG"
                .t()                                                    // 允许打印线程信息，默认禁止
                .b()                                                    // 允许打印日志边框，默认禁止
//                .jsonFormatter(new MyJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
//                .xmlFormatter(new MyXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
//                .throwableFormatter(new MyThrowableFormatter())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
//                .threadFormatter(new MyThreadFormatter())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
//                .stackTraceFormatter(new MyStackTraceFormatter())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
//                .borderFormatter(new MyBoardFormatter())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//                .addObjectFormatter(AnyClass.class,                    // 为指定类添加格式化器
//                        new AnyClassObjectFormatter())                     // 默认使用 Object.toString()
//                .addInterceptor(new BlacklistTagsFilterInterceptor(    // 添加黑名单 TAG 过滤器
//                        "blacklist1", "blacklist2", "blacklist3"))
//                .addInterceptor(new MyInterceptor())                   // 添加一个日志拦截器
                .build();

        Printer androidPrinter = new AndroidPrinter();             // 通过 android.util.Log 打印日志的打印器
        Printer consolePrinter = new ConsolePrinter();             // 通过 System.out 打印日志到控制台的打印器
        Printer filePrinter = new FilePrinter                      // 打印日志到文件的打印器
                .Builder("/sdcard/xlog/")                              // 指定保存日志文件的路径
                .fileNameGenerator(new DateFileNameGenerator())        // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
//                .backupStrategy(new NeverBackupStrategy()              // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
//                        .logFlattener(new MyFlattener())                       // 指定日志平铺器，默认为 DefaultFlattener
                        .build();

        XLog.init(config);
//        XLog.init(                                                 // 初始化 XLog
//                config,                                                // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
//                androidPrinter,                                        // 添加任意多的打印器。如果没有添加任何打印器，会默认使用 AndroidPrinter(Android)/ConsolePrinter(java)
//                consolePrinter,
//                filePrinter);
        XLog.d("onCreate() 应用开始创建");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}