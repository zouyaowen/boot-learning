package com.zou.learning.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.zou.learning.dao.IChannelTempDao;
import com.zou.learning.dao.IMsgTempDao;
import com.zou.learning.dto.MsgParamDTO;
import com.zou.learning.entity.ChannelTempDO;
import com.zou.learning.entity.MsgTempDO;
import com.zou.learning.service.IChannelTempService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zouyaowen
 * @date 2020-05
 */
@Service
@Slf4j
public class ChannelTempServiceImpl implements IChannelTempService {

    @Autowired
    private IChannelTempDao channelTempDao;
    @Autowired
    private IMsgTempDao msgTempDao;

    private static final String smsUrl = "https://messagehub.jiahui.com/sms/send";

    @Override
    public void sendMsg() {
        ArrayList<String> mobileList = new ArrayList<>();
        // mobileList.add("13611604781");
        // mobileList.add("18621863590");
        // mobileList.add("17721017929");

        mobileList.add("16621128563");
        mobileList.add("13501989152");
        mobileList.add("17321094756");

        //mobileList.add("16621792812");
        // 01李 02王 03张 04刘 05陈 06杨 07赵 08黄 09周 10吴
        // 11徐 12孙 13胡 14朱 15高 16林 17何 18郭 19马 20罗
        // 21梁 22宋 23郑 24谢 25韩 26唐 27冯 28于 29董 30萧
        // 31程 32曹 33袁 34邓 35许 36傅 37沈 38曾 39彭 40吕
        // 41苏 42卢 43蒋 44蔡 45贾 46丁 47魏 48薛 49叶 50阎
        // 51余 52潘 53杜 54戴 55夏 56钟 57汪 58田 59任 60姜
        // 61范 62方 63石 64姚 65谭 66廖 67邹 68熊 69金 70陆
        // 71郝 72孔 73白 74崔 75康 76毛 77邱 78秦 79江 80史
        // 81顾 82侯 83邵 84孟 85龙 86万 87段 88漕 89钱 90汤
        // 91尹 92黎 93易 94常 95武 96乔 97贺 98赖 99龚 100文


        //明　国　华　建　文　平　志　伟　东　海　强　晓　生　光　林　小　民　永　杰　军
        //波　成　荣　新　峰　刚　家　龙　德　庆　斌　辉　良　玉　俊　立　浩　天　宏　子 
        //金　健　一　忠　洪　江　福　祥　中　正　振　勇　耀　春　大　宁　亮　宇　兴　宝
        //少　剑　云　学　仁　涛　瑞　飞　鹏　安　亚　泽　世　汉　达　卫　利　胜　敏　群 
        //松　克　清　长　嘉　红　山　贤　阳　乐　锋　智　青　跃　元　南　武　广　思　雄
        //锦　威　启　昌　铭　维　义　宗　英　凯　鸿　森　超　坚　旭　政　传　康　继　翔
        //远　力　进　泉　茂　毅　富　博　霖　顺　信　凡　豪　树　和　恩　向　道　川　彬
        //柏　磊　敬　书　鸣　芳　培　全　炳　基　冠　晖　京　欣　廷　哲　保　秋　君　劲
        //栋　仲　权　奇　礼　楠　炜　友　年　震　鑫　雷　兵　万　星　骏　伦　绍　麟　雨
        //行　才　希　彦　兆　贵　源　有　景　升　惠　臣　慧　开　章　润　高　佳　虎　根
        //诚　夫　声　冬　奎　扬　双　坤　镇　楚　水　铁　喜　之　迪　泰　方　同　滨　邦
        //先　聪　朝　善　非　恒　晋　汝　丹　为　晨　乃　秀　岩　辰　洋　然　厚　灿　卓 
        //轩　帆　若　连　勋　祖　锡　吉　崇　钧　田　石　奕　发　洲　彪　钢　运　伯　满
        //庭　申　湘　皓　承　梓　雪　孟　其　潮　冰　怀　鲁　裕　翰　征　谦　航　士　尧
        //标　洁　城　寿　枫　革　纯　风　化　逸　腾　岳　银　鹤　琳　显　焕　来　心　凤
        //睿　勤　延　凌　昊　西　羽　百　捷　定　琦　圣　佩　麒　虹　如　靖　日　咏　会 
        //久　昕　黎　桂　玮　燕　可　越　彤　雁　孝　宪　萌　颖　艺　夏　桐　月　瑜　沛
        //杨　钰　兰　怡　灵　淇　美　琪　亦　晶　舒　菁　真　涵　爽　雅　爱　依　静　棋
        //宜　男　蔚　芝　菲　露　娜　珊　雯　淑　曼　萍　珠　诗　璇　琴　素　梅　玲　蕾
        //艳　紫　珍　丽　仪　梦　倩　伊　茜　妍　碧　芬　儿　岚　婷　菊　妮　媛　莲　娟

        // "明","国","华","建","文","平","志","伟","东","海","强","晓","生","光","林","小","民","永","杰","军",
        // "波","成","荣","新","峰","刚","家","龙","德","庆","斌","辉","良","玉","俊","立","浩","天","宏","子", 
        // "金","健","一","忠","洪","江","福","祥","中","正","振","勇","耀","春","大","宁","亮","宇","兴","宝",
        // "少","剑","云","学","仁","涛","瑞","飞","鹏","安","亚","泽","世","汉","达","卫","利","胜","敏","群", 
        // "松","克","清","长","嘉","红","山","贤","阳","乐","锋","智","青","跃","元","南","武","广","思","雄",
        // "锦","威","启","昌","铭","维","义","宗","英","凯","鸿","森","超","坚","旭","政","传","康","继","翔",
        // "远","力","进","泉","茂","毅","富","博","霖","顺","信","凡","豪","树","和","恩","向","道","川","彬",
        // "柏","磊","敬","书","鸣","芳","培","全","炳","基","冠","晖","京","欣","廷","哲","保","秋","君","劲",
        // "栋","仲","权","奇","礼","楠","炜","友","年","震","鑫","雷","兵","万","星","骏","伦","绍","麟","雨",
        // "行","才","希","彦","兆","贵","源","有","景","升","惠","臣","慧","开","章","润","高","佳","虎","根",
        // "诚","夫","声","冬","奎","扬","双","坤","镇","楚","水","铁","喜","之","迪","泰","方","同","滨","邦",
        // "先","聪","朝","善","非","恒","晋","汝","丹","为","晨","乃","秀","岩","辰","洋","然","厚","灿","卓", 
        // "轩","帆","若","连","勋","祖","锡","吉","崇","钧","田","石","奕","发","洲","彪","钢","运","伯","满",
        // "庭","申","湘","皓","承","梓","雪","孟","其","潮","冰","怀","鲁","裕","翰","征","谦","航","士","尧",
        // "标","洁","城","寿","枫","革","纯","风","化","逸","腾","岳","银","鹤","琳","显","焕","来","心","凤",
        // "睿","勤","延","凌","昊","西","羽","百","捷","定","琦","圣","佩","麒","虹","如","靖","日","咏","会", 
        // "久","昕","黎","桂","玮","燕","可","越","彤","雁","孝","宪","萌","颖","艺","夏","桐","月","瑜","沛",
        // "杨","钰","兰","怡","灵","淇","美","琪","亦","晶","舒","菁","真","涵","爽","雅","爱","依","静","棋",
        // "宜","男","蔚","芝","菲","露","娜","珊","雯","淑","曼","萍","珠","诗","璇","琴","素","梅","玲","蕾",
        // "艳","紫","珍","丽","仪","梦","倩","伊","茜","妍","碧","芬","儿","岚","婷","菊","妮","媛","莲","娟",

        List<ChannelTempDO> channelTempDOList = channelTempDao.selectAll();
        List<Long> msgIdList = channelTempDOList.stream().map(ChannelTempDO::getMsgTempId).collect(Collectors.toList());
        List<MsgTempDO> byIdList = msgTempDao.getByIdList(msgIdList);
        Map<Long, MsgTempDO> msgTempDOMap = byIdList.stream().collect(Collectors.toMap(MsgTempDO::getId, MsgTempDO -> MsgTempDO));
        for (ChannelTempDO channelTempDO : channelTempDOList) {
            //1、获取参数
            String msgChannelTempParams = channelTempDO.getMsgChannelTempParams();
            MsgTempDO msgTempDO = msgTempDOMap.get(channelTempDO.getMsgTempId());
            if (msgTempDO == null) {
                log.info("数据异常,channelTempDO=[{}]", JSON.toJSONString(channelTempDO));
                throw new RuntimeException("数据异常");
            }
            //2、获取模板编号
            String tempCode = msgTempDO.getTempCode();
            //3、渲染参数
            MsgParamDTO msgParamDTO = new MsgParamDTO();
            msgParamDTO.setTempCode(tempCode);
            msgParamDTO.setAppCode("aaa");
            for (String mobile : mobileList) {
                msgParamDTO.setMobile(mobile);
                if (!StringUtils.isEmpty(msgChannelTempParams)) {
                    JSONObject jsonObject = JSON.parseObject(msgChannelTempParams);
                    Set<String> keySet = jsonObject.keySet();
                    for (String key : keySet) {
                        if (key.contains("NAME")) {
                            jsonObject.put(key, getRandomName());
                            continue;
                        }
                        if (key.contains("TIME")) {
                            Random random = new Random();
                            LocalDate localDate = LocalDate.now().plusDays(random.nextInt(500));
                            jsonObject.put(key, localDate.toString());
                            continue;
                        }
                        jsonObject.put(key, getRandomName());

                    }
                    msgParamDTO.setTempParam(jsonObject);
                }
                HttpRequest httpRequest = HttpRequest.post(smsUrl).header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON);
                HttpRequest send = httpRequest.send(JSON.toJSONString(msgParamDTO));
                int code = send.code();
                String body = send.body();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (code != HttpStatus.OK.value() || StringUtils.isEmpty(body)) {
                    log.error("短信请求失败参数信息param={}", JSON.toJSONString(msgParamDTO));
                    log.error("短信请求失败返回值信息res={}", body);
                }
                log.info("短信请求成功参数信息param={}", JSON.toJSONString(msgParamDTO));
                log.info("短信请求成功返回值信息res={}", body);
            }
        }
    }

    private String getRandomName() {
        ArrayList<String> familyNameList = new ArrayList<>(Arrays.asList("李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴",
                "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗",
                "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧",
                "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕",
                "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "薛", "叶", "阎",
                "余", "潘", "杜", "戴", "夏", "钟", "汪", "田", "任", "姜",
                "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆",
                "郝", "孔", "白", "崔", "康", "毛", "邱", "秦", "江", "史",
                "顾", "侯", "邵", "孟", "龙", "万", "段", "漕", "钱", "汤",
                "尹", "黎", "易", "常", "武", "乔", "贺", "赖", "龚", "文"));
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList("明", "国", "华", "建", "文", "平", "志", "伟", "东", "海", "强", "晓", "生", "光", "林", "小", "民", "永", "杰", "军",
                "波", "成", "荣", "新", "峰", "刚", "家", "龙", "德", "庆", "斌", "辉", "良", "玉", "俊", "立", "浩", "天", "宏", "子",
                "金", "健", "一", "忠", "洪", "江", "福", "祥", "中", "正", "振", "勇", "耀", "春", "大", "宁", "亮", "宇", "兴", "宝",
                "少", "剑", "云", "学", "仁", "涛", "瑞", "飞", "鹏", "安", "亚", "泽", "世", "汉", "达", "卫", "利", "胜", "敏", "群",
                "松", "克", "清", "长", "嘉", "红", "山", "贤", "阳", "乐", "锋", "智", "青", "跃", "元", "南", "武", "广", "思", "雄",
                "锦", "威", "启", "昌", "铭", "维", "义", "宗", "英", "凯", "鸿", "森", "超", "坚", "旭", "政", "传", "康", "继", "翔",
                "远", "力", "进", "泉", "茂", "毅", "富", "博", "霖", "顺", "信", "凡", "豪", "树", "和", "恩", "向", "道", "川", "彬",
                "柏", "磊", "敬", "书", "鸣", "芳", "培", "全", "炳", "基", "冠", "晖", "京", "欣", "廷", "哲", "保", "秋", "君", "劲",
                "栋", "仲", "权", "奇", "礼", "楠", "炜", "友", "年", "震", "鑫", "雷", "兵", "万", "星", "骏", "伦", "绍", "麟", "雨",
                "行", "才", "希", "彦", "兆", "贵", "源", "有", "景", "升", "惠", "臣", "慧", "开", "章", "润", "高", "佳", "虎", "根",
                "诚", "夫", "声", "冬", "奎", "扬", "双", "坤", "镇", "楚", "水", "铁", "喜", "之", "迪", "泰", "方", "同", "滨", "邦",
                "先", "聪", "朝", "善", "非", "恒", "晋", "汝", "丹", "为", "晨", "乃", "秀", "岩", "辰", "洋", "然", "厚", "灿", "卓",
                "轩", "帆", "若", "连", "勋", "祖", "锡", "吉", "崇", "钧", "田", "石", "奕", "发", "洲", "彪", "钢", "运", "伯", "满",
                "庭", "申", "湘", "皓", "承", "梓", "雪", "孟", "其", "潮", "冰", "怀", "鲁", "裕", "翰", "征", "谦", "航", "士", "尧",
                "标", "洁", "城", "寿", "枫", "革", "纯", "风", "化", "逸", "腾", "岳", "银", "鹤", "琳", "显", "焕", "来", "心", "凤",
                "睿", "勤", "延", "凌", "昊", "西", "羽", "百", "捷", "定", "琦", "圣", "佩", "麒", "虹", "如", "靖", "日", "咏", "会",
                "久", "昕", "黎", "桂", "玮", "燕", "可", "越", "彤", "雁", "孝", "宪", "萌", "颖", "艺", "夏", "桐", "月", "瑜", "沛",
                "杨", "钰", "兰", "怡", "灵", "淇", "美", "琪", "亦", "晶", "舒", "菁", "真", "涵", "爽", "雅", "爱", "依", "静", "棋",
                "宜", "男", "蔚", "芝", "菲", "露", "娜", "珊", "雯", "淑", "曼", "萍", "珠", "诗", "璇", "琴", "素", "梅", "玲", "蕾",
                "艳", "紫", "珍", "丽", "仪", "梦", "倩", "伊", "茜", "妍", "碧", "芬", "儿", "岚", "婷", "菊", "妮", "媛", "莲", "娟"));
        Random random = new Random();
        int familyNameIndex = random.nextInt(familyNameList.size());
        int nameIndex1 = random.nextInt(nameList.size());
        int nameIndex2 = random.nextInt(nameList.size());
        return familyNameList.get(familyNameIndex) + nameList.get(nameIndex1) + nameList.get(nameIndex2);
    }
}
