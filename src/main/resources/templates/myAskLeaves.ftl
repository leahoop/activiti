<#include "layout/layout.ftl"/>
<@html page_title="我的任务" page_tab="myAskLeaves">

    <ol class="breadcrumb">
        <li>
            <a href="/">主页</a>
        </li>
        <li class="active">我的申请</li>
    </ol>
    <a class="btn btn-primary btn-sm pull-right" data-toggle="modal" style="margin-bottom: 20px"
       href="#modal-id">新增申请</a>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>标题</th>
            <th>描述</th>
            <th>申请类型</th>
            <th>状态</th>
            <th>日期</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list askLeaves as item>
            <tr>
                <td>${item.askLeave.id?c}</td>
                <td>${item.askLeave.title!}</td>
                <td>${item.askLeave.description!}</td>
                <td>${(item.askLeave.type == 1) ? string('奖学金','助学金')}</td>
                <td>${item.askLeave.status!}</td>
                <td>${item.askLeave.inTime!}</td>
                <td>
                    <#if item.askLeave.status == "创建">
                        <button class="btn btn-xs btn-primary" onclick="publishAskLeave(${item.askLeave.id?c})">提交申请
                        </button>
                    <#else>
                        <button class="btn btn-xs btn-info" onclick="showAskLeaveProcess(${item.askLeave.id?c})">
                            查看处理流程
                        </button>
                        <div class="hidden" id="askLeaveProcessTable_${item.askLeave.id?c}">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>时间</th>
                                    <th>受理人</th>
                                    <th>批注内容</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if item.comments??>
                                    <#list item.comments as comment>
                                        <tr>
                                            <td>${comment.time?string('yyyy-MM-dd HH:mm:ss')}</td>
                                            <td>${comment.userId!}</td>
                                            <td>${comment.message!}</td>
                                        </tr>
                                    </#list>
                                </#if>
                                </tbody>
                            </table>
                        </div>
                        <#if item.askLeave.status == "通过">
                            <button class="btn btn-xs btn-primary" onclick="download(${item.askLeave.type?c})">下载申请表
                            </button>
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

    <div class="modal fade" id="modal-id">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">新增申请</h4>
                </div>
                <div class="modal-body">
                    <form action="" onsubmit="return;" id="form" method="post" role="form">

                        <div class="form-group">
                            <label for="">标题</label>
                            <input type="text" class="form-control" name="" id="title" placeholder="标题">
                        </div>
                        <div class="form-group">
                            <label for="">描述</label>
                            <input type="text" class="form-control" name="" id="description" placeholder="描述">
                        </div>

                        <div class="form-group">
                            <label for="">申请类型：</label>
                            <select id="select_type" name="select_type">
                                <option value="">---请选择---</option>
                                <#list _type as type>
                                    <option value="${type.code}">${type.value}</option>
                                </#list>
                            </select>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="btn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>

    <script>

        $("#btn").click(function () {
            var title = $("#title").val();
            var description = $("#description").val();
            var day = $("#day").val();
            var type = $("#select_type").val();
            $.post("/askLeave/add", {
                title: title,
                type: type,
                description: description,
                status: '创建',
                day: day
            }, function () {
                window.location.reload();
            })
        });

        function publishAskLeave(id) {
            if (confirm("确定要提交申请吗？")) {
                $.post("/askLeave/commit", {id: id}, function () {
                    window.location.reload();
                })
            }
        }

        function showAskLeaveProcess(id) {
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['600px', 'auto'], //宽高
                content: '<div style="padding: 20px;">' + $("#askLeaveProcessTable_" + id).html() + '</div>'
            });
        }

        function download(i) {
            var url = "/download";
            // 创建表单
            var formObj = document.createElement('form');
            formObj.action = url;
            formObj.method = 'get';
            formObj.style.display = 'none';
            // 创建input，主要是起传参作用
            var formItem = document.createElement('input');
            formItem.value = i; // 传参的值
            formItem.name = 'fileType'; // 传参的字段名
            // 插入到网页中
            formObj.appendChild(formItem);
            document.body.appendChild(formObj);
            formObj.submit(); // 发送请求
            document.body.removeChild(formObj); // 发送完清除掉
        }
    </script>
</@html>
