var bConnect=0;
 
function socketload()
{
	loginForm.SocketInfo.value = "";
	//如果是IE10及以下浏览器，则跳过不处理
    if(navigator.userAgent.indexOf("MSIE")>0 && !navigator.userAgent.indexOf("opera") > -1) return;
    try
    {
        var s_pnp=new SoftKey6W();
         s_pnp.Socket_UK.onopen = function() 
        {
               	bConnect=1;
		login_onclick();
        } 
        
       
        s_pnp.Socket_UK.onmessage =function got_packet(Msg) 
        {
            var PnpData = JSON.parse(Msg.data);
            if(PnpData.type=="PnpEvent")
            {
                if(PnpData.IsIn) 
                {
                	loginForm.SocketInfo.value = "UKEY已插入";
                	loginForm.SocketStr.value = "";
                	login_onclick();
                    //alert("UKEY已被插入，被插入的锁的路径是："+PnpData.DevicePath);
                }
                else
                {
                	loginForm.SocketInfo.value = "UKEY未发现";
                	loginForm.SocketStr.value = "";
                	
                	//window.location.href=window.location.host+"/realestate/app/framework/login/logout"; 
                    //alert("UKEY已被拨出，被拨出的锁的路径是："+PnpData.DevicePath);
                }
            }
        } 
        
        s_pnp.Socket_UK.onclose = function()
        {
            
        }
   }
   catch(e)  
   {  
        alert(e.name + ": " + e.message);
        return false;
    }  
}

 var digitArray = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');

function toHex( n ) {

        var result = ''
        var start = true;

        for ( var i=32; i>0; ) {
                i -= 4;
                var digit = ( n >> i ) & 0xf;

                if (!start || digit != 0) {
                        start = false;
                        result += digitArray[digit];
                }
        }

        return ( result == '' ? '0' : result );
}

function login_onclick() 
{
	//如果是IE10及以下浏览器，则使用AVCTIVEX控件的方式
	if(navigator.userAgent.indexOf("MSIE")>0 && !navigator.userAgent.indexOf("opera") > -1) return Handle_IE10();
	
    if(bConnect==0)
    {
        window.alert ( "未能连接服务程序，请确定服务程序是否安装。");return false;
    }
    
   	var DevicePath,ret,n,mylen,ID_1,ID_2,addr,headStr,strLenArr,strStart;
	try
	{
			

			var s_simnew1=new SoftKey6W(); 
			
		    s_simnew1.Socket_UK.onopen = function() {
		   	   s_simnew1.ResetOrder();
		    } 
		    
		   
		    
           
		 s_simnew1.Socket_UK.onmessage =function got_packet(Msg) 
	    {
	        var UK_Data = JSON.parse(Msg.data);
	      
	        if(UK_Data.type!="Process")return ;
	        switch(UK_Data.order) 
	        {
	            case 0:
	                {
	                    s_simnew1.FindPort(0);
	                }
	                break;
	            case 1:
	                {
	                    if( UK_Data.LastError!=0){
	                    	loginForm.SocketInfo.value = "未发现加密锁，请插入加密锁。";
	                    	//window.alert ( "未发现加密锁，请插入加密锁");
	                    	s_simnew1.Socket_UK.close();
	                    	return false;} 
	                    DevicePath=UK_Data.return_value;
	                    s_simnew1.GetID_1(DevicePath); 
	                }
	                break;
	            case 2:
	                {
	                    if( UK_Data.LastError!=0){ window.alert("返回ID号错误，错误码为："+UK_Data.LastError.toString());s_simnew1.Socket_UK.close();return false;} 
	                    ID_1=UK_Data.return_value;
	                    s_simnew1.GetID_2(DevicePath); 
	                }
	                break;
	             case 3:
	                {
	                    if( UK_Data.LastError!=0){ window.alert("取得ID错误，错误码为："+UK_Data.LastError.toString());s_simnew1.Socket_UK.close();return false;} 
	                     ID_2=UK_Data.return_value;
	                     
	                     s_simnew1.ContinueOrder();
	                }
	                break;
	             case 4:
	                {
	                    
			            addr=0;
			            s_simnew1.YReadString(addr,10,"22e0dfd7","22e0dfd7",DevicePath);
	                }
	                break;
	            case 5:
	                {
	                    if( UK_Data.LastError!=0){ window.alert("读取字符串时错误，错误码为："+UK_Data.LastError.toString());s_simnew1.Socket_UK.close();return false;} 
	                   
	                    var lenStr=UK_Data.return_value;
			    var result=lenStr.split(";");
				mylen = Number(result[0])+result[0].length+1;
			   s_simnew1.YReadString(0,mylen, "22e0dfd7", "22e0dfd7", DevicePath);
	                }
	                break;
	            case 6:
	                {
	                   if( UK_Data.LastError!=0){ window.alert("读取字符串时错误，错误码为："+UK_Data.LastError.toString());s_simnew1.Socket_UK.close();return false;} 

	                    loginForm.SocketStr.value = UK_Data.return_value;
	                    loginForm.SocketInfo.value = "UKEY读取成功";
	                }
	                break;
	             case 7:
	                {
	                    if( UK_Data.LastError!=0){ window.alert("进行加密运行算时错误，错误码为："+UK_Data.LastError.toString());s_simnew1.Socket_UK.close();return false;} 
	                  
	                     s_simnew1.Socket_UK.close();
	                }
	                break;
            }
	    } 
	    s_simnew1.Socket_UK.onclose = function(){

	    }
	    
		return true;
	}
	catch (e) 
	{
		alert(e.name + ": " + e.message);
	}
	
}

function Handle_IE10() 
{
	var DevicePath,ret,n,mylen;
	try
	{
	
        var s_simnew1;

		s_simnew1=new ActiveXObject("Syunew6A.s_simnew6");

        
		DevicePath = s_simnew1.FindPort(0);
		if( s_simnew1.LastError!= 0 )
		{
			loginForm.SocketInfo.value = "未发现加密锁，请插入加密锁。";
			//window.alert ( "未发现加密锁，请插入加密锁。");
			
			return false;
		}
		
		if( s_simnew1.LastError!= 0 )
		{
            window.alert( "返回ID号错误，错误码为："+s_simnew1.LastError.toString());
	        return false;
		}
		
		ret=s_simnew1.YRead(0,1,"22e0dfd7","22e0dfd7",DevicePath);
		mylen =s_simnew1.GetBuf(0);
		
	    
		loginForm.SocketInfo.value = "UKEY读取成功";
		loginForm.SocketStr.value=s_simnew1.YReadString(0,2000, "22e0dfd7", "22e0dfd7", DevicePath);
		if( s_simnew1.LastError!= 0 )
		{
			window.alert(  "读取用户名时错误，错误码为："+s_simnew1.LastError.toString());
			return false;
		}
		
		return ;

	}
	catch (e) 
	{
		loginForm.SocketInfo.value = "可能是没有安装相应的控件或插件";
		//alert(e.name + ": " + e.message+"。可能是没有安装相应的控件或插件");
	}
}
