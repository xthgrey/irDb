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

    public static final int modelMatch = 0;//型号匹配
    public static final int intelligentIndex = 1;//智能索引
    public static final int oneKeyMatch = 2;//一键匹配

    public static final int pjt = 0;//投影仪
    public static final int fans = 1;//风扇
    public static final int stb = 2;//机顶盒
    public static final int dvd = 3;//dvd
    public static final int iptv = 4;//网络机顶盒
    public static final int tv = 5;//电视
    public static final int air = 6;//空调
    public static final int dc = 7;//单反
    public static final int ap = 8;//空气净化器
    public static final int water = 9;//热水器
    public static final int audio = 10;//音响

    public final class PJT {
        public static final int PJT_On = 0;   //开机
        public static final int PJT_Off = 1;   //关机
        public static final int PJT_Computer = 2;   //电脑
        public static final int PJT_Video = 3;  //视频
        public static final int PJT_SignalSource = 4;  //信号源
        public static final int PJT_FocusAdd = 5;  //变焦＋
        public static final int PJT_FocusRed = 6;  //变焦－
        public static final int PJT_PictureAdd = 7;  //画面＋
        public static final int PJT_PictureRed = 8;  //画面－
        public static final int PJT_Menu = 9;  //菜单
        public static final int PJT_Confirm = 10;  //确认
        public static final int PJT_Up = 11;  //上
        public static final int PJT_Left = 12; //左
        public static final int PJT_Right = 13;  //右
        public static final int PJT_Down = 14;//下
        public static final int PJT_Quit = 15; //退出
        public static final int PJT_VolAdd = 16; //音量＋
        public static final int PJT_VolRed = 17; //音量－
        public static final int PJT_Mute = 18;//静音
        public static final int PJT_Auto = 19; //自动
        public static final int PJT_Pause = 20; //暂停
        public static final int PJT_MCD = 21; //亮度

    }

    public final class FANS {
        public static final int FANS_On_Off = 0;   //开关
        public static final int FANS_On_speed = 1;   //开／风速
        public static final int FANS_Shake = 2;   //摇头
        public static final int FANS_Mode = 3;   //风类（模式）
        public static final int FANS_Timer = 4;   //定时
        public static final int FANS_Light = 5;  //灯光
        public static final int FANS_Anion = 6; //负离子
        public static final int FANS_1 = 7; //1
        public static final int FANS_2 = 8; //2
        public static final int FANS_3 = 9; //3
        public static final int FANS_4 = 10;  //4
        public static final int FANS_5 = 11;  //5
        public static final int FANS_6 = 12; //6
        public static final int FANS_7 = 13; //7
        public static final int FANS_8 = 14; //8
        public static final int FANS_9 = 15; //9
        public static final int FANS_Sleep = 16;  //睡眠
        public static final int FANS_Cold = 17; //制冷
        public static final int FANS_AirVol = 18; //风量
        public static final int FANS_LowSpeed = 19; //低速
        public static final int FANS_MiddleSpeed = 20; //中速
        public static final int FANS_HighSpeed = 21;  //高速
    }

    public final class STB {
        public static final int STB_Wait = 0;  //待机
        public static final int STB_1 = 1;  //1
        public static final int STB_2 = 2; //2
        public static final int STB_3 = 3; //3
        public static final int STB_4 = 4; //4
        public static final int STB_5 = 5;//5
        public static final int STB_6 = 6; //6
        public static final int STB_7 = 7;//7
        public static final int STB_8 = 8; //8
        public static final int STB_9 = 9; //9
        public static final int STB_Lead = 10;//导视
        public static final int STB_0 = 11; //0
        public static final int STB_Back = 12; //返回
        public static final int STB_Up = 13; //上
        public static final int STB_Left = 14; //左
        public static final int STB_Comfirm = 15; //确定
        public static final int STB_Right = 16; //右
        public static final int STB_Down = 17; //下
        public static final int STB_VolAdd = 18; //声音＋
        public static final int STB_VolRed = 19; //声音－
        public static final int STB_ChAdd = 20; //频道＋
        public static final int STB_ChRed = 21; //频道－
        public static final int STB_Menu = 22; //菜单
    }

    public final class DVD {
        public static final int DVD_Left = 0;   //左
        public static final int DVD_Up = 1;   //上
        public static final int DVD_Ok = 2;  //ok
        public static final int DVD_Down = 3;   //下
        public static final int DVD_Right = 4;   //右
        public static final int DVD_Power = 5;  //电源
        public static final int DVD_Mute = 6; //静音
        public static final int DVD_FBack = 7;  //快倒
        public static final int DVD_Play = 8; //播放
        public static final int DVD_FForwad = 9;  //快进
        public static final int DVD_Last = 10;  //上一曲
        public static final int DVD_Stop = 11; //停止
        public static final int DVD_Next = 12;  //下一曲
        public static final int DVD_Format = 13;  //制式
        public static final int DVD_Pause = 14; //暂停
        public static final int DVD_Title = 15; //标题
        public static final int DVD_SK = 16; //开关仓
        public static final int DVD_Menu = 17;  //静音
        public static final int DVD_Back = 18;   //返回
    }

    public final class IPTV {
        public static final int IPTV_Power = 0;  //电源
        public static final int IPTV_Mute = 1;  //静音
        public static final int IPTV_VolAdd = 2;  //音量＋
        public static final int IPTV_VolRed = 3;  //音量－
        public static final int IPTV_ChAdd = 4;  //频道＋
        public static final int IPTV_ChRed = 5; //频道-
        public static final int IPTV_Up = 6; //上
        public static final int IPTV_Left = 7; //左
        public static final int IPTV_OK = 8; //OK
        public static final int IPTV_Right = 9; //右
        public static final int IPTV_Down = 10; //下
        public static final int IPTV_Play = 11; //播放／暂停
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

    public final class TV {
        public static final int TV_VolAdd = 0;//声音＋
        public static final int TV_ChAdd = 1;//频道＋
        public static final int TV_Menu = 2;//菜单
        public static final int TV_ChRed = 3;//频道－
        public static final int TV_VolRed = 4;//声音－
        public static final int TV_Power = 5;//电源
        public static final int TV_Mute = 6;//静音
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
        public static final int TV_Back = 19;//返回
        public static final int TV_Comfirm = 20;//确定
        public static final int TV_Up = 21;//上
        public static final int TV_Left = 22;//左
        public static final int TV_Right = 23;//右
        public static final int TV_Down = 24;//下
    }

    public final class AIR {
        public static final int AIR_Power = 0;  //电源
        public static final int AIR_Mode = 1;  //模式
        public static final int AIR_Vol = 2;  //风量
        public static final int AIR_M = 3; //手动风向
        public static final int AIR_A = 4; //自动风向
        public static final int AIR_tmpAdd = 5;  //温度＋
        public static final int AIR_tmpRed = 6; //温度－
    }

    public final class DC {
        public static final int DC_TPIC = 0;  //拍照
    }

    public final class AP {
        public static final int AP_Power = 0;  //电源
        public static final int AP_Auto = 1;  //自动
        public static final int AP_AirVol = 2;  //风量
        public static final int AP_Timer = 3;  //定时
        public static final int AP_Mode = 4; //模式
        public static final int AP_Anion = 5; //负离子
        public static final int AP_Comfort = 6; //舒适
        public static final int AP_Mute = 7; //静音
    }
    public final class WATER {
        public static final int WH_Power   = 0;  //电源
        public static final int WH_Set     = 1;  //设置
        public static final int WH_TemAdd  = 2;  //温度＋
        public static final int WH_TemRed  = 3;  //温度－
        public static final int WH_Mode    = 4;  //模式
        public static final int WH_Confirm = 5; //确定
        public static final int WH_Timer   = 6; //定时
        public static final int WH_Ant     = 7 ;//预约
        public static final int WH_Time    = 8; //时间
        public static final int WH_Stem    = 9; //保温

    }
    public final class AUDIO {
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
