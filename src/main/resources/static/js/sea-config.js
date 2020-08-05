seajs.config({

	base : '/realestate/resources/scripts/realestate/registration/',
	// 别名
	alias : {
		'baseBusinessModule' : 'base/baseBusinessModule',// 所有模块的基类
		'baseModule' : 'base/baseModule',// 所有模块的基类
		'baseModuleConifg' : 'base/baseModuleConfig',// 配置模块的基类
		'sqrlb' : 'modules/common/applicantList',// 申请人列表
		'sqspb' : 'modules/common/approvalForm',// 不动产申请审核表
		'sqrxx' : 'modules/common/applicantInfo',// 申请人详细信息
		'dqdcxm' : 'modules/csdj/readSurveyProject',// 读取调查项目
		'dylb' : 'modules/common/unitList',// 单元列表
		'fwdyxx' : 'modules/common/houseInfo',// 房屋单元信息
		'ljzxx' : 'modules/common/logicbuildingInfo',// 逻辑幢单元信息
		'qlrlb' : 'modules/common/rightholderList',// 权利人列表
		'qlrxx' : 'modules/common/rightholderInfo',// 权利人信息
		'qlrxx_dydj' : 'modules/csdjanddydj/rightholderInfo_dydj',// 权利人信息
		'xzfw' : 'modules/common/selectSurveyHouse',// 选择房屋
		'xzzrz' : 'modules/common/selectSurveyBuilding',// 选择自然幢
		'xzsyqzd' : 'modules/common/selectSurveyLand',// 选择所有权宗地
		'zddyxx' : 'modules/common/landInfo',// 宗地单元信息
		'zrzdyxx' : 'modules/common/buildingInfo',// 自然幢单元信息
		'zslb' : 'modules/common/certList',// 证书列表
		'zsxx' : 'modules/common/certInfo',// 证书信息
		'fzxx' : 'modules/common/outcertInfo',// 发证信息
		'sfxx' : 'modules/common/chargeInfo',// 收费信息
		'sflb' : 'modules/common/chargeList',// 收费列表
		'jsydsyqdb' : 'modules/common/landuseBook',// 建设用地使用权登簿
		'fzlb' : 'modules/common/outcertList',// 发证列表
		'fdcqdb' : 'modules/common/singlebuildingBook',// 房地产权登簿
		'fdcqdb_dz' : 'modules/common/multibuildingBook',// 房地产权_独幢登簿
		'djgd' : 'modules/common/archive',// 房地产权_独幢登簿
		'syq' : 'modules/common/rightsInfo',// 权利关系
		'print2' : 'modules/common/print2',// 打印，加2避免关键字
		'book_xqxx' : '../../resources/scripts/realestate/registrationbook/book_xqxx',// 登记簿用到的
		'dycx' : 'modules/dyzxdj/queryMortgageRights',// 查询现状库中的抵押信息
		'zxdjcx' : 'modules/cfdj/zxdjcx',// 注销登记查询
		"prerightsinfo" : 'modules/common/prerightsInfo',
		'dycx' : 'modules/dyzxdj/queryMortgageRights',// 查询现状库中的抵押信息
		// 'xzzyfh_zd':'modules/csdj/xzzyfh_zd',地或房屋信息
		'qlxx_dy' : 'modules/dydj/mortgageInfo',// 抵押权信息
		'dyzxxx' : 'modules/dyzxdj/destroyMortgateInfo',// 抵押权注销信息
		'zddyxx' : 'modules/common/landInfo',// 宗地单元信息（可编辑）
		'pdfdown' : 'modules/common/pdfdown',// 下载PDF文件
		'printTpl' : 'modules/common/printTpl',// 打印模板
		'printTplManage' : 'modules/common/printTplManage',// 打印模板管理
		'boardbook' : 'modules/common/boardbook.js',// 打印模板管理
		'sqspb' : '../../resources/scripts/realestate/fccommon/qjdc/zddc/zd/wts',	//宗地调查表
		'baseQJXMModule':'/realestate/resources/scripts/realestate/fccommon/base/baseQJXMModule' //权籍获取项目编号
	}
});