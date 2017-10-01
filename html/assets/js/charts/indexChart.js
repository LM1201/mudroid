(function(){
	var myChart = echarts.init(document.getElementById("widget-chart-box-1"));
	var myChart2 = echarts.init(document.getElementById("widget-chart-box-2"));
	
	var labelTop = {
		
    normal : {
        label : {
            show : true,
            position : 'center',
            formatter : '{b}',
            textStyle: {
                baseline : 'bottom'
            }
        },
        labelLine : {
            show : false
        }
        
    }
};
var labelFromatter = {
    normal : {
        label : {
            formatter : function (params){
                return 100 - params.value + '%'
            },
            textStyle: {
                baseline : 'center'
            }
        }
    },
}
var labelBottom = {
    normal : {
        color: '#ccc',
        label : {
            show : true,
            position : 'center'
        },
        labelLine : {
            show : false
        }
    },
    emphasis: {
        color: '#ccc'
    }
};
var radius = [40, 35];
option = {
	
	
	
    legend: {
        x : 'center',
        y : 'center',
        
    },
    
    grid: {
    	x:0,
    	y:0,
    	x2:0,
    	y2:0
    },
    
    toolbox: {
        show : true,
        feature : {
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        width: '20%',
                        height: '30%',
                        itemStyle : {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return 'other\n' + params.value + '%\n'
                                    },
                                    textStyle: {
                                        baseline : 'middle'
                                    }
                                }
                            },
                        } 
                    }
                }
            }
           
        }
    },
    series : [
        {
            type : 'pie',
            
            radius : radius,
            x: '0%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:100-90.16, itemStyle : labelBottom},
                {name:'', value: 90.16 ,itemStyle : labelTop}
            ]
        }
        
    ],
    animation :false
};
                    
	
	myChart.setOption(option);
	myChart2.setOption(option);
})();




(function(){
	var myChart = echarts.init(document.getElementById("index-pie-1"));
	
	option = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['TWD','ITR','JDC','IPR','CDL','BWD','ROR','ECR','PRV','JSD','IPC','IOD','VDL']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'center',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'变异算子',
            type:'pie',
            radius : ['50%', '70%'],
            itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '20',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:[{value:14,name:'TWD'},
{value:12,name:'ITR'},
{value:1,name:'JDC'},
{value:5,name:'IPR'},
{value:8,name:'CDL'},
{value:14,name:'BWD'},
{value:8,name:'ROR'},
{value:8,name:'ECR'},
{value:2,name:'PRV'},
{value:3,name:'JSD'},
{value:1,name:'IPC'},
{value:10,name:'IOD'},
{value:7,name:'VDL'},
]
        }
    ]
};
                    
                    
	
	myChart.setOption(option);
})();

(function(){
	var myChart = echarts.init(document.getElementById("index-pie-2"));
	
	option = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['androidOp','xmlOp','classOp','traditionalOp']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'center',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'变异算子类型',
            type:'pie',
            radius : ['50%', '70%'],
            itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '20',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:[{value:25,name:'androidOp'},
{value:28,name:'xmlOp'},
{value:17,name:'classOp'},
{value:23,name:'traditionalOp'},
]
        }
    ]
};
                    
                    
	
	myChart.setOption(option);
})();

(function(){
	var myChart = echarts.init(document.getElementById("index-pie-3"));
	
	option = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['TWD','ROR','ECR','PRV','ITR','JDC','IPR','CDL','IOD','VDL']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'center',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'变异算子',
            type:'pie',
            radius : ['50%', '70%'],
            itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '20',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:[{value:8,name:'TWD'},
{value:8,name:'ROR'},
{value:4,name:'ECR'},
{value:2,name:'PRV'},
{value:12,name:'ITR'},
{value:1,name:'JDC'},
{value:5,name:'IPR'},
{value:5,name:'CDL'},
{value:10,name:'IOD'},
{value:6,name:'VDL'},
]
        }
    ]
};
                    
                    
	
	myChart.setOption(option);
})();


(function(){
	var myChart = echarts.init(document.getElementById("index-pie-4"));
	
	option = {
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['androidOp','xmlOp','classOp','traditionalOp']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        x: '25%',
                        width: '50%',
                        funnelAlign: 'center',
                        max: 1548
                    }
                }
            },
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    series : [
        {
            name:'变异算子类型',
            type:'pie',
            radius : ['50%', '70%'],
            itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '20',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:[{value:21,name:'androidOp'},
{value:8,name:'xmlOp'},
{value:13,name:'classOp'},
{value:19,name:'traditionalOp'},
]
        }
    ]
};
                    
                    
	
	myChart.setOption(option);
})();





(function(){
	var myChart = echarts.init(document.getElementById("index-bar-1"));
	
	option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // é§æ­ç£æå­å¯ç»åæ«éå±½æ½éåªé±çï¹å½éå¤æ¥
            type : 'shadow'        // æ¦æ¨¿î»æ¶è¹æ´¿ç»¾å¡ç´éîï¿½å¤è´éï¿½'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data :['BookDetailsActivity.java','RecyclerBooksAdapter.java','activity_main.xml','MainActivity.java','activity_search_view.xml','activity_action_bar_example.xml','CustomListActivity.java','DateTimePickerActivity.java','DialogExampleActivity.java','Book.java','SearchViewActivity.java','activity_book_details.xml','item_book.xml','DisplayNameActivity.java','RecyclerViewActivity.java','BooksAdapter.java','EnterNameActivity.java','activity_date_time_picker.xml','AppRecentSearchesProvider.java'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'文件数',
            type:'bar',
            barWidth: '60%',
            data:[1,2,1,1,1,1,3,10,3,2,14,2,2,4,1,2,9,1,1]
        }
    ]
};

                    
                    
	
	myChart.setOption(option);
})();

(function(){
	var myChart = echarts.init(document.getElementById("index-bar-2"));
	
	option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // é§æ­ç£æå­å¯ç»åæ«éå±½æ½éåªé±çï¹å½éå¤æ¥
            type : 'shadow'        // æ¦æ¨¿î»æ¶è¹æ´¿ç»¾å¡ç´éîï¿½å¤è´éï¿½'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['BookDetailsActivity.java','activity_main.xml','MainActivity.java','activity_search_view.xml','activity_action_bar_example.xml','activity_enter_name.xml','CustomListActivity.java','DateTimePickerActivity.java','Book.java','activity_spinner_selection.xml','activity_display_name.xml','RecyclerViewActivity.java','BooksAdapter.java','EnterNameActivity.java','AppRecentSearchesProvider.java','RecyclerBooksAdapter.java','activity_searchable.xml','DialogExampleActivity.java','SearchViewActivity.java','activity_book_details.xml','item_book.xml','DisplayNameActivity.java','fragment_books.xml','activity_dialog_example.xml','activity_date_time_picker.xml'],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'文件数',
            type:'bar',
            barWidth: '60%',
            data:[3,10,1,1,2,2,3,11,2,1,1,1,4,11,1,3,1,7,14,2,2,4,1,2,3]
        }
    ]
};

                    
                    
	
	myChart.setOption(option);
})();

(function(){
	var myChart = echarts.init(document.getElementById("index-line-1"));
	
	option = {

    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['éï¿½æ¥æ¨»çµå¨ï¿½','éï¿½æµ£åº¢çµå¨ï¿½'],
        x: "right"
    },
    
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['éã¤ç«´','éã¤ç°©','éã¤ç¬','éã¥æ´','éã¤ç°²','éã¥å','éã¦æ£©']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} æ³C'
        }
    },
    series: [
        {
            name:'éï¿½æ¥æ¨»çµå¨ï¿½',
            type:'line',
            data:[11, 11, 15, 13, 12, 13, 10],
            markPoint: {
                data: [
                    {type: 'max', name: 'éï¿½æ¾¶Ñï¿½ï¿½'},
                    {type: 'min', name: 'éï¿½çå¿ï¿½ï¿½'}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: 'éªå²æ½éï¿½'}
                ]
            }
        },
        {
            name:'éï¿½æµ£åº¢çµå¨ï¿½',
            type:'line',
            data:[1, -2, 2, 5, 3, 2, 0],
            markPoint: {
                data: [
                    {name: 'éã¦æ¸¶æµ£ï¿½', value: -2, xAxis: 1, yAxis: -1.5}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: 'éªå²æ½éï¿½'},
                    [{
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                    }, {
                        symbol: 'circle',
                        label: {
                            normal: {
                                position: 'start',
                                formatter: 'éï¿½æ¾¶Ñï¿½ï¿½'
                            }
                        },
                        type: 'max',
                        name: 'éï¿½æ¥æ¨¼å£'
                    }]
                ]
            }
        }
    ]
};


                    
                    
	
	myChart.setOption(option);
})();

