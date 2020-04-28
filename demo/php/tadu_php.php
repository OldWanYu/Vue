<?php

//////////////////////////////////////////////////////////////////////////////////////////////
// 变量处理部分

// 1. 常量定义
define('sysCPIdTest',true);									// 测试开关  true:测试接口		false:正式接口
define('sysCPCopyrighted',88);									// copyrightid

if(sysCPIdTest){
	// 测试接口调用参数
	define('sysPostUrl',"topenapi.tadu.com");					// url 
	define('sysCPSecre',"********************");				// secre
	define('sysCPPort',8098);									// port
}else{
	// 正式接口调用参数
	define('sysPostUrl',"http://openapi.tadu.com");				// url 
	define('sysCPSecre',"********************");				// secre
	define('sysCPPort',80);										// port
}

// 2. 变量初始化
$bid=;			// CP书籍ID

//////////////////////////////////////////////////////////////////////////////////////////////
// 程序处理部分

// 1. 作品信息处理。检测该作品在对方书库中的信息
$data="";
$data["key"]=sha1(sysCPCopyrighted.sysCPSecre);
$data["cpid"]=$bid;
$data["copyrightid"]=sysCPCopyrighted;

$str=curlPostTaDu(sysPostUrl."/api/getUpdateInfo",sysCPPort, $data);
$corpBkInfo=json_decode($str, true);
echo "塔读最后章节信息：".$corpBkInfo["result"]["chapternum"].":".$corpBkInfo["result"]["chaptername"]."<br>\r\n";

if($corpBkInfo["code"]==-19){
	// 1.1 作品不存在，则创建作品
	$data="";
	$data["key"]=sha1(sysCPCopyrighted.sysCPSecre);
	$data["cpid"]=$bid;
	$data["copyrightid"]=sysCPCopyrighted;
	$data["coverimage"]="封面url";
	$data["bookname"]="作品名称";
	$data["authorname"]="作者名称";
	$data["intro"]="简介";
	$data["classid"]="分类id";
	$data["serial"]="是否连载 1连载0全本";
	$data["isvip"]="是否收费 1收费0免费";
	$data["url"]="原站URL";

	$str=curlPostTaDu(sysPostUrl."/api/addBook", sysCPPort,$data , $head=true);

	$res=json_decode($str, true);
	if($res["code"]<>0){
		echo "新作品添加失败，请和系统管理员联系！错误：".$res["code"];
		exit;
	}else{
		$bookid=$res["result"]["bookid"];	// 作品对应塔读的书号
	}

}else{
	// 1.2 其它状态处理
	if($corpBkInfo["code"]==0){
		// 读取信息成功，数据初始化
		$bookid=$res["result"]["bookid"];	// 作品对应塔读的书号
	}else{
		echo "系统故障，请和系统管理员联系!错误：".$corpBkInfo["code"];
		exit;
	}
}

// 该作品若无塔读对应的书号，不能传章节
if(empty($bookid)){
	echo "bookid不能为空，请和系统管理员联系！";
	exit;
}

// 2. 上传章节
// 2.1 查找待上传章节列表

// $chList 待上传章节数组
if(!empty($chList)){

	// 章节校验上传
	foreach($chList as $chArryChValue){
		$data="";
		$data["key"]=sha1(sysCPCopyrighted.sysCPSecre);
		$data["bookid"]=$bookid;
		$data["copyrightid"]=sysCPCopyrighted;
		$data["title"]="章节名称";
		$data["content"]="章节内容";
		$data["chapternum"]="章节序号";
		$data["isvip"]="是否收费";
		$data["chapterid"]="CP章节ID";
		$data["updatemode"]=1;
	
		$str=curlPostTaDu(sysPostUrl."/api/addChapter",sysCPPort, $data , $head=true);
		$res=json_decode($str, true);
  
		if($res["code"]<>"0"){
			echo "章节信息:<br>\r\n";
			print_r($res);	
			echo "<br>\r\n";
			print_r($res);
			echo "第".$chArryChValue["ch_order"]."章　".trim($chArryChValue["ch_name"])."<br>\r\n";
			echo "添加章节失败，请和系统管理员联系！<br>\r\n";
			echo "错误：".$res["code"];			
			exit;
		}else{
			// 上传成功后的后续处理
		
		}
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////
// 函数部分

// 1. 提交数据
//*****************************************************************
function curlPostTaDu($url, $port, $data , $head=false) {

	// 整理参数
    while (list($k,$v) = each($data)) {
        $encoded .= ($encoded ? "&" : "");
        $encoded .= ($k)."=".($v);
    }

	$curl = curl_init();
	curl_setopt($curl, CURLOPT_POST, true);
	curl_setopt($curl, CURLOPT_POSTFIELDS, $encoded);
	curl_setopt($curl, CURLOPT_HTTPHEADER, 'Content-Type: text/plain');
	// 当服务器不能正常连接塔读服务器时，请尝试使用 CURLOPT_INTERFACE 参数。（双线路设定用联通ip连接塔读服务器）有的电信ip连不到塔读服务器
	// curl_setopt($curl, CURLOPT_INTERFACE, 'eth0');
	curl_setopt($curl, CURLOPT_URL,$url);
	curl_setopt($curl, CURLOPT_PORT,$port);
	curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
	$line=curl_exec($curl);

	if (curl_errno($curl)) echo '<pre><b>错误:</b><br />'.curl_error($curl);

	curl_close($curl);
    return $line;
}

?>