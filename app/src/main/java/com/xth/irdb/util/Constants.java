package com.xth.irdb.util;

/**
 * Created by XTH on 2018/1/2.
 */

public class Constants {

    public static final String dynamic_ir_data = "dynamic_ir_data";

    public static final String[][] fileName = {
            {"stb_pjt_table", "stb_fan_table", "stb_data_table", "dvd_data_table", "remote_IPTV_table", "tv_table", "arc_table", "remote_SLR_table", "remote_air_purifier_table", "remote_Water_Heater_table", "remote_Audio_table"},
            {"remote_pjt_info", "remote_fan_info", "remote_stb_info", "remote_dvd_info", "remote_IPTV_info", "TV_info", "g_remote_arc_info", "remote_SLR_info", "remote_air_purifier_info", "remote_Water_Heater_info", "remote_Audio_info"},
            {"remote_pjt_2_info", "remote_fan_2_info", "remote_stb_2_info", "remote_dvd_2_info", "remote_IPTV_2_info", "remote_tv_2_info", "g_remote_arc_2_info", "remote_SLR_2_info", "remote_air_purifier_2_info", "remote_Water_Heater_2_info", "remote_Audio_2_info"},
            {"all_pjt_one_key", "all_fan_one_key", "brand_stb_one_key", "brand_dvd_one_key", "all_IPTV_one_key", "brand_tv_one_key", "all_arc_one_key", "all_SLR_one_key", "all_air_purifier_one_key", "all_Water_Heater_one_key", "brand_Audio_one_key"}
    };

    public static final int IR_PJT = 0;//投影仪
    public static final int IR_FANS = 1;//风扇
    public static final int IR_STB = 2;//机顶盒
    public static final int IR_DVD = 3;//dvd
    public static final int IR_IPTV = 4;//网络机顶盒
    public static final int IR_TV = 5;//电视
    public static final int IR_AIR = 6;//空调
    public static final int IR_DC = 7;//单反
    public static final int IR_AP = 8;//空气净化器
    public static final int IR_WATER = 9;//热水器
    public static final int IR_AUDIO = 10;//音响

    public final class IR_PJT_CLASS {
        public static final int PJT_ON = 0;   //开机
        public static final int PJT_OFF = 1;   //关机
        public static final int PJT_COMPUTER = 2;   //电脑
        public static final int PJT_VIDEO = 3;  //视频
        public static final int PJT_SIGNALSOURCE = 4;  //信号源
        public static final int PJT_FOCUSADD= 5;  //变焦＋
        public static final int PJT_FOCUSRED = 6;  //变焦－
        public static final int PJT_PICTUREADD = 7;  //画面＋
        public static final int PJT_PICTURERED = 8;  //画面－
        public static final int PJT_MENU = 9;  //菜单
        public static final int PJT_CONFIRM = 10;  //确认
        public static final int PJT_UP = 11;  //上
        public static final int PJT_LEFT = 12; //左
        public static final int PJT_RIGHT = 13;  //右
        public static final int PJT_DOWN = 14;//下
        public static final int PJT_QUIT = 15; //退出
        public static final int PJT_VOLADD = 16; //音量＋
        public static final int PJT_VOLRED = 17; //音量－
        public static final int PJT_MUTE = 18;//静音
        public static final int PJT_AUTO = 19; //自动
        public static final int PJT_PAUSE = 20; //暂停
        public static final int PJT_MCD = 21; //亮度

    }

    public final class IR_FANS_CLASS {
        public static final int FANS_POWER = 0;   //电源
        public static final int FANS_SPEED = 1;   //风速
        public static final int FANS_SHAKE = 2;   //摇头
        public static final int FANS_MODE = 3;   //风类（模式）
        public static final int FANS_TIMER = 4;   //定时
        public static final int FANS_LIGHT = 5;  //灯光
        public static final int FANS_ANION = 6; //负离子
        public static final int FANS_1 = 7; //1
        public static final int FANS_2 = 8; //2
        public static final int FANS_3 = 9; //3
        public static final int FANS_4 = 10;  //4
        public static final int FANS_5 = 11;  //5
        public static final int FANS_6 = 12; //6
        public static final int FANS_7 = 13; //7
        public static final int FANS_8 = 14; //8
        public static final int FANS_9 = 15; //9
        public static final int FANS_SLEEP = 16;  //睡眠
        public static final int FANS_COLD = 17; //制冷
        public static final int FANS_AIRVOL = 18; //风量
        public static final int FANS_LOW_SPEED = 19; //低速
        public static final int FANS_MIDDLE_SPEED = 20; //中速
        public static final int FANS_HIGH_SPEED = 21;  //高速
    }

    public final class IR_STB_CLASS {
        public static final int STB_WAIT = 0;  //待机
        public static final int STB_1 = 1;  //1
        public static final int STB_2 = 2; //2
        public static final int STB_3 = 3; //3
        public static final int STB_4 = 4; //4
        public static final int STB_5 = 5;//5
        public static final int STB_6 = 6; //6
        public static final int STB_7 = 7;//7
        public static final int STB_8 = 8; //8
        public static final int STB_9 = 9; //9
        public static final int STB_LEAD = 10;//导视
        public static final int STB_0 = 11; //0
        public static final int STB_BACK = 12; //返回
        public static final int STB_UP = 13; //上
        public static final int STB_LEFT = 14; //左
        public static final int STB_CONFIRM = 15; //确定
        public static final int STB_RIGHT = 16; //右
        public static final int STB_DOWN = 17; //下
        public static final int STB_VOLADD = 18; //声音＋
        public static final int STB_VOLRED = 19; //声音－
        public static final int STB_CHADD = 20; //频道＋
        public static final int STB_CHRED = 21; //频道－
        public static final int STB_MENU = 22; //菜单
    }

    public final class IR_DVD_CLASS {
        public static final int DVD_LEFT = 0;   //左
        public static final int DVD_UP = 1;   //上
        public static final int DVD_OK = 2;  //ok
        public static final int DVD_DOWN = 3;   //下
        public static final int DVD_RIGHT = 4;   //右
        public static final int DVD_POWER = 5;  //电源
        public static final int DVD_MUTE = 6; //静音
        public static final int DVD_FAST_BACK = 7;  //快倒
        public static final int DVD_Play = 8; //播放
        public static final int DVD_FAST_FORWORD = 9;  //快进
        public static final int DVD_LAST = 10;  //上一曲
        public static final int DVD_STOP = 11; //停止
        public static final int DVD_NEXT = 12;  //下一曲
        public static final int DVD_FORMAT = 13;  //制式
        public static final int DVD_PAUSE = 14; //暂停
        public static final int DVD_TITLE = 15; //标题
        public static final int DVD_SK = 16; //开关仓
        public static final int DVD_MENU = 17;  //菜单
        public static final int DVD_BACK = 18;   //返回
    }

    public final class IR_IPTV_CLASS {
        public static final int IPTV_POWER = 0;  //电源
        public static final int IPTV_MUTE = 1;  //静音
        public static final int IPTV_VOLADD = 2;  //音量＋
        public static final int IPTV_VOLRED = 3;  //音量－
        public static final int IPTV_CHADD = 4;  //频道＋
        public static final int IPTV_CHRED = 5; //频道-
        public static final int IPTV_UP = 6; //上
        public static final int IPTV_LEFT = 7; //左
        public static final int IPTV_OK = 8; //OK
        public static final int IPTV_RIGHT = 9; //右
        public static final int IPTV_DOWN = 10; //下
        public static final int IPTV_PLAY = 11; //播放／暂停
        public static final int IPTV_1 = 12; //1
        public static final int IPTV_2 = 13; //2
        public static final int IPTV_3 = 14; //3
        public static final int IPTV_4 = 15; //4
        public static final int IPTV_5 = 16; //5
        public static final int IPTV_6 = 17; //6
        public static final int IPTV_7 = 18; //7
        public static final int IPTV_8 = 19; //8
        public static final int IPTV_9 = 20; //9
        public static final int IPTV_0 = 21; //0
        public static final int IPTV_BACK = 22;  //返回

    }

    public final class IR_TV_CLASS {
        public static final int TV_VOLADD = 0;//声音＋
        public static final int TV_CHADD = 1;//频道＋
        public static final int TV_MENU = 2;//菜单
        public static final int TV_CHRED = 3;//频道－
        public static final int TV_VOLRED = 4;//声音－
        public static final int TV_POWER = 5;//电源
        public static final int TV_MUTE = 6;//静音
        public static final int TV_1 = 7;//1
        public static final int TV_2 = 8;//2
        public static final int TV_3 = 9;//3
        public static final int TV_4 = 10;//4
        public static final int TV_5 = 11;//5
        public static final int TV_6 = 12;//6
        public static final int TV_7 = 13;//7
        public static final int TV_8 = 14;//8
        public static final int TV_9 = 15;//9
        public static final int TV_Res = 16;//--/-
        public static final int TV_0 = 17;//0
        public static final int TV_AV_TV = 18;//AV/TV
        public static final int TV_BACK = 19;//返回
        public static final int TV_CONFIRM = 20;//确定
        public static final int TV_UP = 21;//上
        public static final int TV_LEFT = 22;//左
        public static final int TV_RIGHT = 23;//右
        public static final int TV_DOWN = 24;//下
    }

    public final class IR_AIR_CLASS {
        public static final int AIR_POWER = 0;  //电源
        public static final int AIR_MODE = 1;  //模式
        public static final int AIR_VOL = 2;  //风量
        public static final int AIR_M = 3; //手动风向
        public static final int AIR_A = 4; //自动风向
        public static final int AIR_TMP_ADD = 5;  //温度＋
        public static final int AIR_TMP_RED = 6; //温度－
    }

    public final class IR_DC_CLASS {
        public static final int DC_TPIC = 0;  //拍照
    }

    public final class IR_AP_CLASS {
        public static final int AP_POWER = 0;  //电源
        public static final int AP_AUTO = 1;  //自动
        public static final int AP_AIRVOL = 2;  //风量
        public static final int AP_TIMER = 3;  //定时
        public static final int AP_MODE = 4; //模式
        public static final int AP_ANION = 5; //负离子
        public static final int AP_COMFORT = 6; //舒适
        public static final int AP_MUTE = 7; //静音
    }
    public final class IR_WATER_CLASS {
        public static final int WH_POWER   = 0;  //电源
        public static final int WH_SET     = 1;  //设置
        public static final int WH_TMP_ADD  = 2;  //温度＋
        public static final int WH_TMP_RED  = 3;  //温度－
        public static final int WH_MODE    = 4;  //模式
        public static final int WH_CONFIRM = 5; //确定
        public static final int WH_TIMER   = 6; //定时
        public static final int WH_ANT     = 7 ;//预约
        public static final int WH_TIME    = 8; //时间
        public static final int WH_STEM    = 9; //保温

    }
    public final class IR_AUDIO_CLASS {
        public static final int AUDIO_LEFT = 0;//左
        public static final int AUDIO_UP = 1;//上
        public static final int AUDIO_OK = 2;//ok
        public static final int AUDIO_DOWN = 3;//下
        public static final int AUDIO_RIGHT = 4;//右
        public static final int AUDIO_POWER = 5;//电源
        public static final int AUDIO_VOLUME_IN = 6;//声音+
        public static final int AUDIO_VOLUME_MUTE = 7;//静音
        public static final int AUDIO_VOLUME_OUT = 8;//声音-
        public static final int AUDIO_FAST_BACK = 9;//后退
        public static final int AUDIO_PLAY = 10;//播放
        public static final int AUDIO_FAST_FORWARD = 11;//前进
        public static final int AUDIO_SONG_UP = 12;//上一曲
        public static final int AUDIO_STOP = 13;//停止
        public static final int AUDIO_SONG_DOWN = 14;//下一曲
        public static final int AUDIO_PAUSE = 15;//暂停
        public static final int AUDIO_MENU = 16;//菜单
        public static final int AUDIO_BACK = 17;//退出
    }
}
