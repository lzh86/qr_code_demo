/*******************************************************************************
 *
 * chenhl
 */
var commonCurd = {
	
	/**
	 * 新增
	 * @param $width layer宽度
	 * @param $height layer高度
	 * @param editUrl 跳转到编辑页面url
	 * @param saveUrl 保存url
	 */
	_new:function($width, $height, editUrl, saveUrl, dg){
		var width = $(top.document).width() * 0.9;
		var height = $(top.document).height() * 0.95;
		top.layer.open({
			type : 2,
			title : ['新增信息' , true],
			area : [ $width + 'px', $height + 'px' ], // 宽高
			shade : 0.4,
			index : 521,
			content : editUrl + "?url=" + saveUrl,
			zIndex : 10000000000, // 重点1
			success : function(layero2) {
				top.layer.setTop(layero2); // 重点2
			},
			end : function() {
				commonCurd._refresh(dg);
			}
		});
		
		/*$(frame).attr("src",editUrl + "?url=" + saveUrl);
//        	var html = $(dlg).html();
		    var $width;
		    var $height;
		    var $id = dlg.substr(1);
		    if($(dlg).length > 0){
		    	$width = $(dlg)[0].style.width;
		    	$height = $(dlg)[0].style.height;
    		}
		    
		    $(dlg).dialog({
	    		title : "添加",
	    		modal: true
	    	});
		    $(dlg).dialog('open');
        	var dig = window.top.$(html).appendTo(window.top.document.body);
        	dig.dialog({
        		title : "新增信息",
        		id : $id,
        		modal: true,
        		width: $width,
        		height:$height,
        		bgiframe:true,
        		close: function(event, ui){
        			this.dialog('close');
                }
        	});*/
    },
    
    /**
     * 关闭dialog
     * @author diaoliwei
     */
    _closeDlg : function(){
    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
    },
    
    /**
     * 刷新
     * @param dg 列表tableID
     * @author diaoliwei
     */
    _refresh : function(dg){
    	$(dg).datagrid('reload'); // reload the user data
		$(dg).treegrid('reload')
    },
	
    /**
     * 保存
     * @param fm  formID
     * @param dg  列表tableID
     */
	_save:function(fm, dg) {
		var url = $(fm).attr("action");
		$(fm).form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success == "true") {
					$.messager.show({
						title : '提示信息',
						msg : "保存成功!"
					});
					commonCurd._closeDlg(); // close the dialog
				} else {
					$.messager.show({
						title : '提示信息',
						msg : result.msg
					});
				}
			}
		});
	},
	
	/**
	 * 编辑
	 * @param $width layer宽度
	 * @param $height layer高度
	 * @param editUrl  跳转到编辑页面url
	 * @param saveUrl  保存操作url
	 */
    _edit:function($width, $height, dg, editUrl, saveUrl){
    	var row = $(dg).datagrid('getSelected');
    	if (row){
    		var width = $(top.document).width() * 0.9;
    		var height = $(top.document).height() * 0.95;
    		top.layer.open({
    			type : 2,
    			title : ['编辑信息' , true],
    			area : [ $width + 'px', $height + 'px' ], // 宽高
    			shade : 0.4,
    			index : 521,
    			content : editUrl + "?url=" + saveUrl + row.id + "&id=" + row.id,
    			zIndex : 10000000000, // 重点1
    			success : function(layero2) {
    				top.layer.setTop(layero2); // 重点2
    			},
    			end : function() {
    				commonCurd._refresh(dg);
    			}
    		});
    	} else{
    		$.messager.alert({
                title: '提示信息',
                msg: "请选择行"
            });
    	}
    	
    	/* var row = $(dg).datagrid('getSelected');
    	 if (row){
    		$(frame).attr("src",editUrl + "?url=" + saveUrl + row.id + "&id=" + row.id);
    	   	var html = $(dlg).html();
    	    var $width;
    	    var $height;
    	    var $id = dlg.substr(1);
    	    if($(dlg).length > 0){
    	    	$width = $(dlg)[0].style.width;
    	    	$height = $(dlg)[0].style.height;
    		}
    	   	var dig = window.top.$(html).appendTo(window.top.document.body);
    	   	dig.dialog({
    	   		title : "修改信息",
    	   		id : $id,
    	   		modal: true,
    	   		width: $width,
    	   		height: $height,
    	   		bgiframe: true,
    	  		close: function(event, ui){
    	   			this.dialog('close');
    	        }
    	   	});
    	 }*/
    },
	
    /**
     * 删除
     * @author diaoliwei
     * @param dg  列表tableID
     * @param url 删除操作的url
     */
	_delete : function(dg, url){
		var row = $(dg).datagrid('getSelected');
        if (row){
            $.messager.confirm('提示信息','你确定删除吗?',function(r){
                if (r){
                    $.ajax({
                        url: url + row.id,
                        type: 'DELETE',
                        dataType: 'json',
                        success: function(result) {
                        	if (result.success=="true"){
                                $(dg).datagrid('reload');    // reload the data
                                if(undefined != $(dg).treegrid.defaults.view.treeNodes){//方便treegrid通用
                                	$(dg).treegrid('reload');
    							}
                            } else {
                            	$.messager.show({    // show error message
                                    title: '提示信息',
                                    msg: result.msg
                                });
                            }
                        }
                    });
                }
            });
        } else{
        	$.messager.alert({
                title: '提示信息',
                msg: "请选择行"
            });
        }
   }
};