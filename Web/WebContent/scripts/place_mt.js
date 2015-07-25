//province(省份类)
function provinceList()
{
    this.length=35;
    this[0] = new Option("不限","不限");
    this[1] = new Option("北京","北京");
    this[2] = new Option("重庆","重庆");
    this[3] = new Option("福建","福建");
    this[4] = new Option("甘肃","甘肃");
    this[5] = new Option("广东","广东");
    this[6] = new Option("广西","广西");
    this[7] = new Option("贵州","贵州");
    this[8] = new Option("海南","海南");
    this[9] = new Option("河北","河北");
    this[10] = new Option("河南","河南");
    this[11] = new Option("黑龙江","黑龙江");
    this[12] = new Option("湖北","湖北");
    this[13] = new Option("湖南","湖南");
    this[14] = new Option("江苏","江苏");
    this[15] = new Option("江西","江西");
    this[16] = new Option("吉林","吉林");
    this[17] = new Option("辽宁","辽宁");
    this[18] = new Option("内蒙古","内蒙古");
    this[19] = new Option("宁夏","宁夏");
    this[20] = new Option("青海","青海");
    this[21] = new Option("上海","上海");
    this[22] = new Option("山东","山东");
    this[23] = new Option("山西","山西");
    this[24] = new Option("陕西","陕西");
    this[25] = new Option("四川","四川");
    this[26] = new Option("天津","天津");
    this[27] = new Option("新疆","新疆");
    this[28] = new Option("西藏","西藏");
    this[29] = new Option("云南","云南");
    this[30] = new Option("浙江","浙江");
    this[31] = new Option("安徽","安徽");
    this[32] = new Option("香港","香港");
    this[33] = new Option("澳门","澳门");
    this[34] = new Option("台湾","台湾");
    return this;
}
//city(城市类)
function citylist()
{
	this.length=35;
	this[0] = new Array(1);
	this[0][0] = new Option("不限","不限");
    this[1] = new Array(1);
    this[1][0] = new Option("北京","北京");
    this[2] = new Array(4);
    this[2][0] = new Option("重庆","重庆");
    this[2][1] = new Option("涪陵","涪陵");
	this[2][2] = new Option("三峡","三峡");
	this[2][3] = new Option("万州","万州");
    this[3] = new Array(19);
    this[3][0] = new Option("福州","福州");
    this[3][1] = new Option("漳浦","漳浦");
    this[3][2] = new Option("南平","南平");
    this[3][3] = new Option("宁德","宁德");
    this[3][4] = new Option("莆田","莆田");
    this[3][5] = new Option("泉州","泉州");
    this[3][6] = new Option("三明","三明");
    this[3][7] = new Option("厦门","厦门");
	this[3][8] = new Option("漳州","漳州");
	this[3][9] = new Option("福清","福清");
	this[3][10] = new Option("晋江","晋江");
	this[3][11] = new Option("龙海","龙海");
	this[3][14] = new Option("南安","南安");
	this[3][15] = new Option("石狮","石狮");
	this[3][16] = new Option("武夷","武夷");
	this[3][17] = new Option("武夷山","武夷山");
	this[3][18] = new Option("兴化","兴化");
    this[4] = new Array(15);
    this[4][0] = new Option("兰州","兰州");
    this[4][1] = new Option("甘南","甘南");
    this[4][2] = new Option("定西","定西");
    this[4][3] = new Option("白银","白银");
    this[4][4] = new Option("嘉峪关","嘉峪关");
    this[4][5] = new Option("金昌","金昌");
    this[4][6] = new Option("酒泉","酒泉");
    this[4][7] = new Option("临夏","临夏");
    this[4][8] = new Option("陇南","陇南");
    this[4][9] = new Option("平凉","平凉");
    this[4][10] = new Option("庆阳","庆阳");
    this[4][11] = new Option("天水","天水");
    this[4][12] = new Option("临夏","临夏");
    this[4][13] = new Option("张掖","张掖");
    this[4][14] = new Option("敦煌","敦煌");

    this[5] = new Array(42);
    this[5][0] = new Option("广州","广州");
    this[5][1] = new Option("佛山","佛山");
    this[5][2] = new Option("惠州","惠州");
    this[5][3] = new Option("东莞","东莞");
    this[5][4] = new Option("江门","江门");
    this[5][5] = new Option("揭阳","揭阳");
    this[5][6] = new Option("潮州","潮州");
    this[5][7] = new Option("茂名","茂名");
    this[5][8] = new Option("梅州","梅州");
    this[5][9] = new Option("清远","清远");
    this[5][10] = new Option("汕头","汕头");
    this[5][11] = new Option("汕尾","汕尾");
    this[5][12] = new Option("深圳","深圳");
    this[5][13] = new Option("韶关","韶关");
    this[5][14] = new Option("阳江","阳江");
    this[5][15] = new Option("河源","河源");
    this[5][16] = new Option("云浮","云浮");
    this[5][17] = new Option("中山","中山");
    this[5][18] = new Option("珠海","珠海");
    this[5][19] = new Option("湛江","湛江");
	this[5][20] = new Option("肇庆","肇庆");
	this[5][21] = new Option("从化","从化");
	this[5][22] = new Option("高州","高州");
	this[5][23] = new Option("鹤山","鹤山");
	this[5][24] = new Option("化州","化州");
	this[5][25] = new Option("花都","花都");
	this[5][26] = new Option("惠东","惠东");
	this[5][27] = new Option("开平","开平");
	this[5][28] = new Option("连州","连州");
	this[5][29] = new Option("廉江","廉江");
	this[5][30] = new Option("南雄","南雄");
	this[5][31] = new Option("南沙","南沙");
	this[5][32] = new Option("普宁","普宁");
	this[5][33] = new Option("番禺","番禺");
	this[5][34] = new Option("平湖","平湖");
	this[5][35] = new Option("四会","四会");
	this[5][36] = new Option("顺德","顺德");
	this[5][37] = new Option("台山","台山");
	this[5][38] = new Option("吴川","吴川");
	this[5][39] = new Option("兴宁","兴宁");
	this[5][40] = new Option("英德","英德");
	this[5][41] = new Option("增城","增城");


    this[6] = new Array(18);
    this[6][0] = new Option("南宁","南宁");
    this[6][1] = new Option("防城港","防城港");
    this[6][2] = new Option("北海","北海");
    this[6][3] = new Option("百色","百色");
    this[6][4] = new Option("贺州","贺州");
    this[6][5] = new Option("桂林","桂林");
	this[6][6] = new Option("桂平","桂平");
	this[6][7] = new Option("阳朔","阳朔");
    this[6][8] = new Option("崇左","崇左");
    this[6][9] = new Option("钦州","钦州");
    this[6][10] = new Option("贵港","贵港");
    this[6][11] = new Option("梧州","梧州");
    this[6][12] = new Option("河池","河池");
	this[6][13] = new Option("玉林","玉林");
	this[6][14] = new Option("北流","北流");
	this[6][15] = new Option("东兴","东兴");
	this[6][16] = new Option("桂平","桂平");
	this[6][17] = new Option("阳朔","阳朔");

    this[7] = new Array(10);
    this[7][0] = new Option("贵阳","贵阳");
    this[7][1] = new Option("毕节","毕节");
    this[7][2] = new Option("遵义","遵义");
    this[7][3] = new Option("安顺","安顺");
    this[7][4] = new Option("六盘水","六盘水");
    this[7][5] = new Option("黔东南","黔东南");
    this[7][6] = new Option("黔南","黔南");
    this[7][7] = new Option("黔西南","黔西南");
	this[7][8] = new Option("铜仁","铜仁");
	this[7][9] = new Option("仁怀","仁怀");

    this[8] = new Array(9);
    this[8][0] = new Option("海口","海口");
    this[8][1] = new Option("三亚","三亚");
	this[8][2] = new Option("万宁","万宁");
	this[8][3] = new Option("儋州","儋州");
	this[8][4] = new Option("东方","东方");
	this[8][5] = new Option("陵水","陵水");
	this[8][6] = new Option("琼海","琼海");
	this[8][7] = new Option("文昌","文昌");
	this[8][8] = new Option("万宁","万宁");


    this[9] = new Array(25);
    this[9][0] = new Option("石家庄","石家庄");
    this[9][1] = new Option("邯郸","邯郸");
    this[9][2] = new Option("邢台","邢台");
    this[9][3] = new Option("保定","保定");
    this[9][4] = new Option("张家口","张家口");
    this[9][5] = new Option("沧州","沧州");
    this[9][6] = new Option("承德","承德");
    this[9][7] = new Option("廊坊","廊坊");
    this[9][8] = new Option("秦皇岛","秦皇岛");
    this[9][9] = new Option("唐山","唐山");
	this[9][10] = new Option("衡水","衡水");
	this[9][11] = new Option("藁城","藁城");
	this[9][12] = new Option("高碑店","高碑店");
	this[9][13] = new Option("黄骅","黄骅");
	this[9][14] = new Option("霸州","霸州");
	this[9][15] = new Option("晋州","晋州");
	this[9][16] = new Option("鹿泉","鹿泉");
	this[9][17] = new Option("滦南","滦南");
	this[9][18] = new Option("迁安","迁安");
	this[9][19] = new Option("沙河","沙河");
	this[9][20] = new Option("三河","三河");
	this[9][21] = new Option("武安","武安");
	this[9][22] = new Option("辛集","辛集");
	this[9][23] = new Option("遵化","遵化");
	this[9][24] = new Option("涿州","涿州");

    this[10] = new Array(39);
    this[10][0] = new Option("郑州","郑州");
    this[10][1] = new Option("开封","开封");
    this[10][2] = new Option("驻马店","驻马店");
    this[10][3] = new Option("安阳","安阳");
    this[10][4] = new Option("焦作","焦作");
    this[10][5] = new Option("洛阳","洛阳");
    this[10][6] = new Option("濮阳","濮阳");
    this[10][7] = new Option("漯河","漯河");
    this[10][8] = new Option("南阳","南阳");
    this[10][9] = new Option("平顶山","平顶山");
    this[10][10] = new Option("新乡","新乡");
    this[10][11] = new Option("信阳","信阳");
    this[10][12] = new Option("许昌","许昌");
    this[10][13] = new Option("商丘","商丘");
    this[10][14] = new Option("三门峡","三门峡");
    this[10][15] = new Option("鹤壁","鹤壁");
    this[10][16] = new Option("周口","周口");
	this[10][17] = new Option("济源","济源");
	this[10][18] = new Option("博爱","博爱");
	this[10][19] = new Option("长葛","长葛");
	this[10][20] = new Option("邓州","邓州");
	this[10][21] = new Option("登封","登封");
	this[10][22] = new Option("巩义","巩义");
	this[10][23] = new Option("灵宝","灵宝");
	this[10][24] = new Option("孟州","孟州");
	this[10][25] = new Option("沁阳","沁阳");
	this[10][26] = new Option("汝州","汝州");
	this[10][27] = new Option("温县","温县");
	this[10][28] = new Option("舞钢","舞钢");
	this[10][29] = new Option("武陟","武陟");
	this[10][30] = new Option("新密","新密");
	this[10][31] = new Option("修武","修武");
	this[10][32] = new Option("新郑","新郑");
	this[10][33] = new Option("荥阳","荥阳");
	this[10][34] = new Option("项城","项城");
	this[10][35] = new Option("永城","永城");
	this[10][36] = new Option("禹州","禹州");
	this[10][37] = new Option("伊川","伊川");
	this[10][38] = new Option("偃师","偃师");


    this[11] = new Array(18);
    this[11][0] = new Option("哈尔滨","哈尔滨");
    this[11][1] = new Option("大庆","大庆");
    this[11][2] = new Option("大兴安岭","大兴安岭");
    this[11][3] = new Option("鸡西","鸡西");
    this[11][4] = new Option("佳木斯","佳木斯");
    this[11][5] = new Option("牡丹江","牡丹江");
    this[11][6] = new Option("齐齐哈尔","齐齐哈尔");
    this[11][7] = new Option("七台河","七台河");
    this[11][8] = new Option("双鸭山","双鸭山");
    this[11][9] = new Option("绥化","绥化");
    this[11][10] = new Option("伊春","伊春");
    this[11][11] = new Option("鹤岗","鹤岗");
	this[11][12] = new Option("黑河","黑河");
	this[11][13] = new Option("海林","海林");
	this[11][14] = new Option("密山","密山");
	this[11][15] = new Option("漠河","漠河");
	this[11][16] = new Option("讷河","讷河");
	this[11][17] = new Option("尚志","尚志");


    this[12] = new Array(30);
    this[12][0] = new Option("武汉","武汉");
    this[12][1] = new Option("黄冈","黄冈");
    this[12][2] = new Option("黄石","黄石");
    this[12][3] = new Option("恩施","恩施");
    this[12][4] = new Option("鄂州","鄂州");
    this[12][5] = new Option("荆门","荆门");
    this[12][6] = new Option("荆州","荆州");
    this[12][7] = new Option("孝感","孝感");
    this[12][8] = new Option("枝江","枝江");
    this[12][9] = new Option("十堰","十堰");
    this[12][10] = new Option("枣阳","枣阳");
    this[12][11] = new Option("咸宁","咸宁");
    this[12][12] = new Option("宜昌","宜昌");
	this[12][13] = new Option("随州","随州");
	this[12][14] = new Option("赤壁","赤壁");
	this[12][15] = new Option("大治","大治");
	this[12][16] = new Option("利川","利川");
	this[12][17] = new Option("麻城","麻城");
	this[12][18] = new Option("潜江","潜江");
	this[12][19] = new Option("神农架","神农架");
	this[12][20] = new Option("松滋","松滋");
	this[12][21] = new Option("天门","天门");
	this[12][22] = new Option("武当山","武当山");
	this[12][23] = new Option("武穴","武穴");
	this[12][24] = new Option("襄阳","襄阳");
	this[12][25] = new Option("仙桃","仙桃");
	this[12][26] = new Option("宜都","宜都");
	this[12][27] = new Option("应城","应城");
	this[12][28] = new Option("宜城","宜城");
	this[12][29] = new Option("钟祥","钟祥");

    this[13] = new Array(27);
    this[13][0] = new Option("长沙","长沙");
    this[13][1] = new Option("怀化","怀化");
    this[13][2] = new Option("郴州","郴州");
    this[13][3] = new Option("常德","常德");
    this[13][4] = new Option("娄底","娄底");
    this[13][5] = new Option("邵阳","邵阳");
    this[13][6] = new Option("湘潭","湘潭");
    this[13][7] = new Option("湘西","湘西");
    this[13][8] = new Option("衡阳","衡阳");
    this[13][9] = new Option("永州","永州");
    this[13][10] = new Option("益阳","益阳");
    this[13][11] = new Option("岳阳","岳阳");
    this[13][12] = new Option("株洲","株洲");
	this[13][13] = new Option("张家界","张家界");
	this[13][14] = new Option("凤凰","凤凰");
	this[13][15] = new Option("洪江","洪江");
	this[13][16] = new Option("华容","华容");
	this[13][17] = new Option("吉首","吉首");
	this[13][18] = new Option("醴陵","醴陵");
	this[13][19] = new Option("冷水江","冷水江");
	this[13][20] = new Option("汨罗","汨罗");
	this[13][21] = new Option("冥王星","冥王星");
	this[13][22] = new Option("宁乡","宁乡");
	this[13][23] = new Option("韶山","韶山");
	this[13][24] = new Option("湘阴","湘阴");
	this[13][25] = new Option("湘乡","湘乡");
	this[13][26] = new Option("沅江","沅江");

    this[14] = new Array(45);
    this[14][0] = new Option("南京","南京");
    this[14][1] = new Option("淮安","淮安");
    this[14][2] = new Option("常州","常州");
    this[14][3] = new Option("连云港","连云港");
    this[14][4] = new Option("南通","南通");
    this[14][5] = new Option("徐州","徐州");
    this[14][6] = new Option("苏州","苏州");
    this[14][7] = new Option("无锡","无锡");
    this[14][8] = new Option("盐城","盐城");
    this[14][9] = new Option("扬州","扬州");
    this[14][10] = new Option("镇江","镇江");
    this[14][11] = new Option("泰州","泰州");
	this[14][12] = new Option("宿迁","宿迁");
	this[14][13] = new Option("宝应","宝应");
	this[14][14] = new Option("滨海","滨海");
	this[14][15] = new Option("常熟","常熟");
	this[14][16] = new Option("丹阳","丹阳");
	this[14][17] = new Option("大丰","大丰");
	this[14][18] = new Option("东台","东台");
	this[14][19] = new Option("阜宁","阜宁");
	this[14][20] = new Option("高邮","高邮");
	this[14][21] = new Option("海门","海门");
	this[14][21] = new Option("海安","海安");
	this[14][22] = new Option("句容","句容");
	this[14][23] = new Option("江阴","江阴");
	this[14][24] = new Option("江宁","江宁");
	this[14][25] = new Option("金坛","金坛");
	this[14][26] = new Option("靖江","靖江");
	this[14][27] = new Option("建湖","建湖");
	this[14][28] = new Option("昆山","昆山");
	this[14][29] = new Option("溧阳","溧阳");
	this[14][30] = new Option("邳州","邳州");
	this[14][31] = new Option("启东","启东");
	this[14][32] = new Option("如皋","如皋");
	this[14][33] = new Option("射阳","射阳");
	this[14][34] = new Option("沭阳","沭阳");
	this[14][35] = new Option("泰兴","泰兴");
	this[14][36] = new Option("太仓","太仓");
	this[14][37] = new Option("吴江","吴江");
	this[14][38] = new Option("响水","响水");
	this[14][39] = new Option("新沂","新沂");
	this[14][40] = new Option("宜兴","宜兴");
	this[14][41] = new Option("扬中","扬中");
	this[14][42] = new Option("仪征","仪征");
	this[14][43] = new Option("周庄","周庄");
	this[14][44] = new Option("张家港","张家港");

    this[15] = new Array(20);
    this[15][0] = new Option("南昌","南昌");
    this[15][1] = new Option("抚州","抚州");
    this[15][2] = new Option("赣州","赣州");
    this[15][3] = new Option("吉安","吉安");
    this[15][4] = new Option("景德镇","景德镇");
    this[15][5] = new Option("九江","九江");
    this[15][6] = new Option("萍乡","萍乡");
    this[15][7] = new Option("新余","新余");
    this[15][8] = new Option("上饶","上饶");
    this[15][9] = new Option("鹰潭","鹰潭");
	this[15][10] = new Option("宜春","宜春");
	this[15][11] = new Option("井冈山","井冈山");
	this[15][12] = new Option("灵山","灵山");
	this[15][13] = new Option("乐平","乐平");
	this[15][14] = new Option("庐山","庐山");
	this[15][15] = new Option("瑞昌","瑞昌");
	this[15][16] = new Option("瑞金","瑞金");
	this[15][17] = new Option("三清山","三清山");
	this[15][18] = new Option("婺源","婺源");
	this[15][19] = new Option("樟树","樟树");

    this[16] = new Array(15);
    this[16][0] = new Option("长春","长春");
    this[16][1] = new Option("白城","白城");
    this[16][2] = new Option("白山","白山");
    this[16][3] = new Option("吉林","吉林");
    this[16][4] = new Option("辽源","辽源");
    this[16][5] = new Option("四平","四平");
    this[16][6] = new Option("松原","松原");
    this[16][7] = new Option("通化","通化");
	this[16][8] = new Option("延边","延边");
	this[16][9] = new Option("公主岭","公主岭");
	this[16][10] = new Option("集安","集安");
	this[16][11] = new Option("梅河口","梅河口");
	this[16][12] = new Option("舒兰","舒兰");
	this[16][13] = new Option("洮南","洮南");
	this[16][14] = new Option("延吉","延吉");

    this[17] = new Array(25);
    this[17][0] = new Option("沈阳","沈阳");
    this[17][1] = new Option("大连","大连");
    this[17][2] = new Option("阜新","阜新");
    this[17][3] = new Option("抚顺","抚顺");
    this[17][4] = new Option("本溪","本溪");
    this[17][5] = new Option("鞍山","鞍山");
    this[17][6] = new Option("丹东","丹东");
    this[17][7] = new Option("锦州","锦州");
    this[17][8] = new Option("朝阳","朝阳");
    this[17][9] = new Option("辽阳","辽阳");
    this[17][10] = new Option("盘锦","盘锦");
    this[17][11] = new Option("铁岭","铁岭");
    this[17][12] = new Option("营口","营口");
	this[17][13] = new Option("葫芦岛","葫芦岛");
	this[17][14] = new Option("灯塔","灯塔");
	this[17][15] = new Option("东港","东港");
	this[17][16] = new Option("调兵山","调兵山");
	this[17][17] = new Option("大石桥","大石桥");
	this[17][18] = new Option("凤城","凤城");
	this[17][19] = new Option("盖州","盖州");
	this[17][20] = new Option("海城","海城");
	this[17][21] = new Option("凌海","凌海");
	this[17][22] = new Option("兴城","兴城");
	this[17][23] = new Option("新民","新民");
	this[17][24] = new Option("庄河","庄河");


    this[18] = new Array(14);
    this[18][0] = new Option("呼和浩特","呼和浩特");
    this[18][1] = new Option("阿拉善盟","阿拉善盟");
    this[18][2] = new Option("巴彦淖尔","巴彦淖尔");
    this[18][3] = new Option("包头","包头");
    this[18][4] = new Option("赤峰","赤峰");
    this[18][5] = new Option("兴安盟","兴安盟");
    this[18][6] = new Option("乌兰察布","乌兰察布");
    this[18][7] = new Option("乌海","乌海");
    this[18][8] = new Option("锡林郭勒","锡林郭勒");
    this[18][9] = new Option("呼伦贝尔","呼伦贝尔");
	this[18][10] = new Option("满洲里","满洲里");
	this[18][11] = new Option("通辽","通辽");
	this[18][12] = new Option("鄂尔多斯","鄂尔多斯");
	this[18][13] = new Option("额尔古纳","额尔古纳");

    this[19] = new Array(5);
    this[19][0] = new Option("银川","银川");
    this[19][1] = new Option("固原","固原");
    this[19][2] = new Option("石嘴山","石嘴山");
    this[19][3] = new Option("吴忠","吴忠");
    this[19][4] = new Option("中卫","中卫");
    
    this[20] = new Array(8);
    this[20][0] = new Option("西宁","西宁");
    this[20][1] = new Option("海东","海东");
    this[20][2] = new Option("海南州","海南州");
    this[20][3] = new Option("海北","海北");
    this[20][4] = new Option("黄南","黄南");
    this[20][5] = new Option("果洛","果洛");
    this[20][6] = new Option("玉树","玉树");
    this[20][7] = new Option("海西","海西");
    
    this[21] = new Array(3);
	this[21][0] = new Option("上海","上海");
	this[21][1] = new Option("宝山","宝山");
	this[21][2] = new Option("大通","大通");

    this[22] = new Array(44);
    this[22][0] = new Option("济南","济南");
    this[22][1] = new Option("东营","东营");
    this[22][2] = new Option("滨州","滨州");
    this[22][3] = new Option("淄博","淄博");
    this[22][4] = new Option("德州","德州");
    this[22][5] = new Option("济宁","济宁");
    this[22][6] = new Option("聊城","聊城");
    this[22][7] = new Option("临沂","临沂");
    this[22][8] = new Option("莱芜","莱芜");
    this[22][9] = new Option("青岛","青岛");
    this[22][10] = new Option("日照","日照");
    this[22][11] = new Option("威海","威海");
    this[22][12] = new Option("泰安","泰安");
    this[22][13] = new Option("潍坊","潍坊");
    this[22][14] = new Option("烟台","烟台");
    this[22][15] = new Option("菏泽","菏泽");
	this[22][16] = new Option("枣庄","枣庄");
	this[22][17] = new Option("安丘","安丘");
	this[22][18] = new Option("昌邑","昌邑");
	this[22][19] = new Option("肥城","肥城");
	this[22][20] = new Option("高密","高密");
	this[22][21] = new Option("广饶","广饶");
	this[22][22] = new Option("海阳","海阳");
	this[22][23] = new Option("胶州","胶州");
	this[22][24] = new Option("即墨","即墨");
	this[22][25] = new Option("龙口","龙口");
	this[22][26] = new Option("莱西","莱西");
	this[22][27] = new Option("乐陵","乐陵");
	this[22][28] = new Option("莱州","莱州");
	this[22][29] = new Option("平度","平度");
	this[22][30] = new Option("蓬莱","蓬莱");
	this[22][31] = new Option("青州","青州");
	this[22][32] = new Option("乳山","乳山");
	this[22][33] = new Option("荣成","荣成");
	this[22][34] = new Option("寿光","寿光");
	this[22][35] = new Option("滕州","滕州");
	this[22][36] = new Option("文登","文登");
	this[22][37] = new Option("新泰","新泰");
	this[22][38] = new Option("兖州","兖州");
	this[22][39] = new Option("禹城","禹城");
	this[22][40] = new Option("诸城","诸城");
	this[22][41] = new Option("邹平","邹平");
	this[22][42] = new Option("章丘","章丘");
	this[22][43] = new Option("招远","招远");


    this[23] = new Array(21);
    this[23][0] = new Option("太原","太原");
    this[23][1] = new Option("大同","大同");
    this[23][2] = new Option("晋城","晋城");
    this[23][3] = new Option("晋中","晋中");
    this[23][4] = new Option("长治","长治");
    this[23][5] = new Option("临汾","临汾");
    this[23][6] = new Option("吕梁","吕梁");
    this[23][7] = new Option("忻州","忻州");
    this[23][8] = new Option("朔州","朔州");
    this[23][9] = new Option("阳泉","阳泉");
	this[23][10] = new Option("运城","运城");
	this[23][11] = new Option("汾阳","汾阳");
	this[23][12] = new Option("高平","高平");
	this[23][13] = new Option("古交","古交");
	this[23][14] = new Option("霍州","霍州");
	this[23][15] = new Option("侯马","侯马");
	this[23][16] = new Option("河津","河津");
	this[23][17] = new Option("介休","介休");
	this[23][18] = new Option("孝义","孝义");
	this[23][19] = new Option("永济","永济");
	this[23][20] = new Option("原平","原平");

    this[24] = new Array(12);
    this[24][0] = new Option("西安","西安");
    this[24][1] = new Option("宝鸡","宝鸡");
    this[24][2] = new Option("安康","安康");
    this[24][3] = new Option("商洛","商洛");
    this[24][4] = new Option("铜川","铜川");
    this[24][5] = new Option("渭南","渭南");
    this[24][6] = new Option("咸阳","咸阳");
    this[24][7] = new Option("延安","延安");
    this[24][8] = new Option("汉中","汉中");
    this[24][9] = new Option("榆林","榆林");
    this[24][10] = new Option("华阴","华阴");
    this[24][11] = new Option("兴平","兴平");

    this[25] = new Array(32);
    this[25][0] = new Option("成都","成都");
    this[25][1] = new Option("达川市","达川市");
    this[25][2] = new Option("甘孜","甘孜");
    this[25][3] = new Option("自贡","自贡");
    this[25][4] = new Option("阿坝","阿坝");
    this[25][5] = new Option("巴中","巴中");
    this[25][6] = new Option("德阳","德阳");
    this[25][7] = new Option("广安","广安");
    this[25][8] = new Option("广元","广元");
    this[25][9] = new Option("凉山","凉山");
    this[25][10] = new Option("乐山","乐山");
    this[25][11] = new Option("攀枝花","攀枝花");
    this[25][12] = new Option("南充","南充");
    this[25][13] = new Option("内江","内江");
    this[25][14] = new Option("泸州","泸州");
    this[25][15] = new Option("绵阳","绵阳");
    this[25][16] = new Option("遂宁","遂宁");
    this[25][17] = new Option("雅安","雅安");
    this[25][18] = new Option("宜宾","宜宾");
    this[25][19] = new Option("眉山","眉山");
	this[25][20] = new Option("资阳","资阳");
	this[25][21] = new Option("安岳","安岳");
	this[25][22] = new Option("崇州","崇州");
	this[25][23] = new Option("都江堰","都江堰");
	this[25][24] = new Option("达州","达州");
	this[25][25] = new Option("峨眉山","峨眉山");
	this[25][26] = new Option("广汉","广汉");
	this[25][27] = new Option("简阳","简阳");
	this[25][28] = new Option("九寨沟","九寨沟");
	this[25][29] = new Option("阆中","阆中");
	this[25][30] = new Option("彭州","彭州");
	this[25][31] = new Option("邛崃","邛崃");

    this[26] = new Array(2);
	this[26][0] = new Option("天津","天津");
	this[26][1] = new Option("塘沽","塘沽");
	
    this[27] = new Array(19);
    this[27][0] = new Option("乌鲁木齐","乌鲁木齐");
    this[27][1] = new Option("喀什","喀什");
    this[27][2] = new Option("克州","克州");
    this[27][3] = new Option("克拉玛依","克拉玛依");
    this[27][4] = new Option("阿克苏","阿克苏");
    this[27][5] = new Option("阿勒泰","阿勒泰");
    this[27][6] = new Option("巴州","巴州");
    this[27][7] = new Option("哈密","哈密");
    this[27][8] = new Option("博尔塔拉","博尔塔拉");
    this[27][9] = new Option("昌吉","昌吉");
    this[27][10] = new Option("塔城","塔城");
    this[27][11] = new Option("吐鲁番","吐鲁番");
    this[27][12] = new Option("和田","和田");
    this[27][13] = new Option("石河子","石河子");
	this[27][14] = new Option("伊犁","伊犁");
	this[27][15] = new Option("阜康","阜康");
	this[27][16] = new Option("库尔勒","库尔勒");
	this[27][17] = new Option("奎屯","奎屯");
	this[27][18] = new Option("伊宁","伊宁");

    this[28] = new Array(7);
    this[28][0] = new Option("拉萨","拉萨");
    this[28][1] = new Option("阿里","阿里");
    this[28][2] = new Option("昌都","昌都");
    this[28][3] = new Option("林芝","林芝");
    this[28][4] = new Option("那曲","那曲");
    this[28][5] = new Option("山南","山南");
    this[28][6] = new Option("日喀则","日喀则");
    
    this[29] = new Array(18);
    this[29][0] = new Option("昆明","昆明");
    this[29][1] = new Option("大理","大理");
    this[29][2] = new Option("昭通","邵通");
    this[29][3] = new Option("腾冲","腾冲");
    this[29][4] = new Option("德宏","德宏");
    this[29][5] = new Option("迪庆","迪庆");
    this[29][6] = new Option("楚雄","楚雄");
    this[29][7] = new Option("临沧","临沧");
    this[29][8] = new Option("丽江","丽江");
    this[29][9] = new Option("怒江","怒江");
    this[29][10] = new Option("曲靖","曲靖");
    this[29][11] = new Option("香格里拉","香格里拉");
    this[29][12] = new Option("西双版纳","西双版纳");
    this[29][13] = new Option("文山","文山");
    this[29][14] = new Option("红河","红河");
    this[29][15] = new Option("玉溪","玉溪");
    this[29][16] = new Option("个旧","个旧");
    this[29][17] = new Option("普洱","普洱");

    this[30] = new Array(42);
    this[30][0] = new Option("杭州","杭州");
    this[30][1] = new Option("嘉兴","嘉兴");
    this[30][2] = new Option("金华","金华");
    this[30][3] = new Option("衢州","衢州");
    this[30][4] = new Option("丽水","丽水");
    this[30][5] = new Option("宁波","宁波");
    this[30][6] = new Option("绍兴","绍兴");
    this[30][7] = new Option("台州","台州");
    this[30][8] = new Option("温州","温州");
    this[30][9] = new Option("湖州","湖州");
	this[30][10] = new Option("舟山","舟山");
	this[30][11] = new Option("安吉","安吉");
	this[30][12] = new Option("苍南","苍南");
	this[30][13] = new Option("慈溪","慈溪");
	this[30][14] = new Option("淳安","淳安");
	this[30][15] = new Option("长兴","长兴");
	this[30][16] = new Option("东阳","东阳");
	this[30][17] = new Option("德清","德清");
	this[30][18] = new Option("富阳","富阳");
	this[30][19] = new Option("奉化","奉化");
	this[30][20] = new Option("海宁","海宁");
	this[30][21] = new Option("建德","建德");
	this[30][22] = new Option("江山","江山");
	this[30][23] = new Option("嘉善","嘉善");
	this[30][24] = new Option("临海","临海");
	this[30][25] = new Option("宁海","宁海");
	this[30][26] = new Option("瑞安","瑞安");
	this[30][27] = new Option("嵊州","嵊州");
	this[30][28] = new Option("上虞","上虞");
	this[30][29] = new Option("桐庐","桐庐");
	this[30][30] = new Option("桐乡","桐乡");
	this[30][31] = new Option("乌镇","乌镇");
	this[30][32] = new Option("温岭","温岭");
	this[30][33] = new Option("萧山","萧山");
	this[30][34] = new Option("象山","象山");
	this[30][35] = new Option("西塘","西塘");
	this[30][36] = new Option("永康","永康");
	this[30][37] = new Option("余姚","余姚");
	this[30][38] = new Option("乐清","乐清");
	this[30][39] = new Option("义乌","义乌");
	this[30][40] = new Option("玉环","玉环");
	this[30][41] = new Option("诸暨","诸暨");


    this[31] = new Array(20);
    this[31][0] = new Option("合肥","合肥");
    this[31][1] = new Option("淮北","淮北");
    this[31][2] = new Option("淮南","淮南");
    this[31][3] = new Option("黄山","黄山");
    this[31][4] = new Option("安庆","安庆");
    this[31][5] = new Option("蚌埠","蚌埠");
    this[31][6] = new Option("巢湖","巢湖");
    this[31][7] = new Option("池州","池州");
    this[31][8] = new Option("滁州","滁州市");
    this[31][9] = new Option("六安","六安");
    this[31][10] = new Option("马鞍山","马鞍山");
    this[31][11] = new Option("宣城","宣城");
    this[31][12] = new Option("宿州","宿州");
    this[31][13] = new Option("铜陵","铜陵");
    this[31][14] = new Option("芜湖","芜湖");
    this[31][15] = new Option("阜阳","阜阳");
    this[31][16] = new Option("亳州","毫州");
    this[31][17] = new Option("明光","明光");
	this[31][18] = new Option("天长","天长");
	this[31][19] = new Option("桐城","桐城");

    this[32] = new Array(1);
    this[32][0] = new Option("香港","香港");
    
    this[33] = new Array(1);
    this[33][0] = new Option("澳门","澳门");
    
    this[34] = new Array(1);
    this[34][0] = new Option("台北","台北");
    
    return this;
}
//创建provincelist、citylist类实例
var provinceOb=new provinceList();
var cityOb=new citylist();
//定义province、city变量，用于select元素
var province;
var city;
//子函数添加城市
function addCitys(province,city)
{
    var index=province.selectedIndex;
    for(var i=0;i<cityOb[index].length;i++)
    {
        try
        {
            city.add(cityOb[index][i]);
        }
        catch(e)
        {
            city.add(cityOb[index][i],null);
        }
    }
}
//子函数删除城市
function delCitys(city)
{
    city.length=0;
}
//初始化地区下拉菜单
function initialize(privinceId,cityId)
{
    //获取select元素
    province=document.getElementById("province");
    city=document.getElementById("city");
    
    //循环添加省份到province
    for(var i=0;i<provinceOb.length;i++)
    {
        try
        {
            province.add(provinceOb[i]);
        }
        catch(e)
        {
            province.add(provinceOb[i],null);
        }
     }
        
        //初始化privinceId
        if(privinceId==undefined)
        {
            privinceId=0;
        }
        //设置province默认选项
        province.options[privinceId].selected=true;
        
        //添加城市到city
        addCitys(province,city);
        //设置city的默认选项
        if(cityId!=undefined)
        {
            city.options[cityId].selected=true;
        }
        else
        {  
            city.options[0].selected=true;
        }        
}
  //下拉列表改变事件
    function selectchange(province,city)
    {
        delCitys(city);
        addCitys(province,city);
    }
