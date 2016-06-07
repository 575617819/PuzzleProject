package com.example.administrator.a0607_notifitiondemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * 基本的Notification
     * @param v
     */
    public void basic1(View v){

        //第一步:用PendingIntent封装Intent(处理延迟操作)
        Intent intent = new Intent(this,BasicActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //第二步：创建Notification.Builder对象，增加Notification属性
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent);//点击后跳转的目标Activity
        builder.setAutoCancel(true);//点击后自动消失
        builder.setSmallIcon(R.drawable.phone);//应用小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.large));//应用大图标
        builder.setContentTitle("这是基本的Notification");//Notification的标题
        builder.setContentText("this is a basic Notification");//Notification的正文
        builder.setSubText("这是备注信息");//Notification的最后一行
        builder.setColor(Color.RED);//小图标背景

        //第三步：Android系统通过NotificationManager来帮我们管理Notification，并通过调用notify方法显示Notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }


    public void basic2(View v){

        //第一步:用PendingIntent封装Intent(处理延迟操作)
        Intent intent = new Intent(this,BasicActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,0);

        //第二步：创建Notification.Builder对象，增加Notification属性
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.phone2);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.phone));
        builder.setContentTitle("探探最新消息");
        builder.setContentText("来自天津的马宁给你发来一条新消息");
        builder.setSubText("我打算去八维和阿豪一款学习，你看怎么样？");
        builder.setColor(Color.BLUE);

        //第三步：Android系统通过NotificationManager来帮我们管理Notification，并通过调用notify方法显示Notification
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(2,builder.build());

    }

    /**
     * 折叠式的Notification
     * @param v
     */
    public void collapsed(View v){

        //先创建自定义视图Notification

        //第一步:用PendingIntent封装Intent(处理延迟操作)
        Intent intent = new Intent(this,CollapsedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //第二步：创建Notification.Builder对象，并添加属性
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.phone);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.phone2));
       // builder.setFullScreenIntent(pendingIntent,true);//将Notification设置成悬挂式

        //第三步：通过RemoteViews来创建自定义的Notification视图(notification.xml和notification_expanded.xml)
        RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.notification);
        Notification notification = builder.build();
        notification.contentView=contentView;

        RemoteViews remoteViews_expanded = new RemoteViews(getPackageName(),R.layout.notification_expanded);
        notification.bigContentView=remoteViews_expanded;

        //第四步：将Notification显示出来
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(3,notification);

    }

    /**
     * 悬挂式Notification增加setFullScreenIntent属性即可
     */

}
