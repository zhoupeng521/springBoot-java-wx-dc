<html>
    <head>
        <meta charset="UTF-8" />
        <title>卖家订单列表</title>
    </head>
    <link rel="stylesheet" href="../../static/css/bootstrap.css" type="text/css" />
    <link href="https://www.bootcss.com/p/layoutit/ckeditor/skins/moono/editor.css?t=D3NA" rel="stylesheet" type="text/css">
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        买家名称
                    </th>
                    <th>
                        买家手机号码
                    </th>
                    <th>
                        买家微信openId
                    </th>
                    <th>
                        订单总额
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        支付状态
                    </th>
                    <th>
                        创建时间
                    </th>
                </tr>
                </thead>
                <tbody>
                   <#list orderDtoPage.content as orderDto>
                       <tr>
                           <td>${orderDto.orderId}</td>
                           <td>${orderDto.buyerName}</td>
                           <td>${orderDto.buyerPhone}</td>
                           <td>${orderDto.buyerAddress}</td>
                           <td>${orderDto.buyerOpenid}</td>
                           <td>${orderDto.orderAmount}</td>
                           <td>${orderDto.orderStatus}</td>
                           <td>${orderDto.payStatus}</td>
                           <td>${orderDto.createTime}</td>
                           <td>${orderDto.updateTime}</td>
                       </tr>
                   </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
