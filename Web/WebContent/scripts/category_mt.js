//parent(顶级目录类)
function parentList()
{
    this.length=3;
    this[0] = new Option("请选择","请选择");
    this[1] = new Option("周边商家",0);
    this[2] = new Option("全城商家",1);
    return this;
}
//son(子目录类)
function sonlist()
{
	this.length=3;
	this[0] = new Array(1);
	this[0][0] = new Option("请选择","请选择");
    this[1] = new Array(7);
    this[1][0] = new Option("美食外卖","美食外卖");
    this[1][1] = new Option("商场超市","商场超市");
    this[1][2] = new Option("家政服务","家政服务");
    this[1][3] = new Option("金融理财","金融理财");
	this[1][4] = new Option("美容美发","美容美发");
	this[1][5] = new Option("疏通开锁","疏通开锁");
	this[1][6] = new Option("家电维修","家电维修");
	this[2] = new Array(3);
	this[2][0] = new Option("搬家装修","搬家装修");
    this[2][1] = new Option("车辆租赁","车辆租赁");
    this[2][2] = new Option("出行娱乐","出行娱乐");
    return this;
}
//创建parentList、sonlist类实例
var parentOb=new parentList();
var sonOb=new sonlist();
//定义parent、son变量，用于select元素
var parent;
var son;
//子函数添加子目录
function addSons(parent,son)
{
    var index=parent.selectedIndex;
    for(var i=0;i<sonOb[index].length;i++)
    {
        try
        {
            son.add(sonOb[index][i]);
        }
        catch(e)
        {
            son.add(sonOb[index][i],null);
        }
    }
}
//子函数删除子目录
function delSons(son)
{
    son.length=0;
}
//初始化地区下拉菜单
function initialized(parentId,sonId)
{
    //获取select元素
    parent=document.getElementById("shopType");
    son=document.getElementById("contentName");
    
    //循环添加顶级目录到parent
    for(var i=0;i<parentOb.length;i++)
    {
        try
        {
            parent.add(parentOb[i]);
        }
        catch(e)
        {
            parent.add(parentOb[i],null);
        }
     }
        
        //初始化parentId
        if(parentId==undefined)
        {
            parentId=0;
        }
        //设置parent默认选项
        parent.options[parentId].selected=true;
        
        //添加子目录到son
        addSons(parent,son);
        //设置son的默认选项
        if(sonId!=undefined)
        {
            son.options[sonId].selected=true;
        }
        else
        {  
            son.options[0].selected=true;
        }        
}
  //下拉列表改变事件
    function selectedchange(parent,son)
    {
        delSons(son);
        addSons(parent,son);
    }
