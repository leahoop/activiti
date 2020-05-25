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
                            <button class="btn btn-xs btn-primary" onclick="download('${item.askLeave.type?c}',
                            '${item.askLeave.id?c}')">下载申请表
                            </button>
                        </#if>
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>

<#--    <div class="modal fade" id="modal-id">-->
<#--        <div class="modal-dialog">-->
<#--            <div class="modal-content">-->
<#--                <div class="modal-header">-->
<#--                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>-->
<#--                    <h4 class="modal-title">新增申请</h4>-->
<#--                </div>-->
<#--                <div class="modal-body">-->
<#--                    <form action="" onsubmit="return;" id="form" method="post" role="form">-->

<#--                        <div class="form-group">-->
<#--                            <label for="">标题</label>-->
<#--                            <input type="text" class="form-control" name="" id="title" placeholder="标题">-->
<#--                        </div>-->
<#--                        <div class="form-group">-->
<#--                            <label for="">描述</label>-->
<#--                            <input type="text" class="form-control" name="" id="description" placeholder="描述">-->
<#--                        </div>-->

<#--                        <div class="form-group">-->
<#--                            <label for="">申请类型：</label>-->
<#--                            <select id="select_type" name="select_type">-->
<#--                                <option value="">---请选择---</option>-->
<#--                                <#list _type as type>-->
<#--                                    <option value="${type.code}">${type.value}</option>-->
<#--                                </#list>-->
<#--                            </select>-->
<#--                        </div>-->

<#--                    </form>-->
<#--                </div>-->
<#--                <div class="modal-footer">-->
<#--                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>-->
<#--                    <button type="button" id="btn" class="btn btn-primary">提交</button>-->
<#--                </div>-->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->

    <div class="modal fade" id="modal-id">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">新增申请</h4>
                </div>

                <div class="modal-body">

                    <form action="" role="form" id="form" class="form-inline" onsubmit="return;">
                        <div class="form-group">
                            <label for="">申请类型：</label>
                            <select id="select_type" name="select_type">
                                <option value="">---请选择---</option>
                                <#list _type as type>
                                    <option value="${type.code}">${type.value}</option>
                                </#list>
                            </select>
                        </div>

                        <h4>本人情况</h4>
                        <div style="margin-left: 20px;border: 2px">

                            <div class="row">
                                <div class="form-group">
                                    <label for="name">姓名：</label>
                                    <input type="text" class="form-control" id="name">
                                </div>
                                <div class="form-group">
                                    <label for="name">性别：</label>
                                    <input type="text" class="form-control" id="sex">
                                </div>

                                <div class="form-group">
                                    <label for="name">出生年月：</label>
                                    <input type="text" class="form-control" id="birthday">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group">
                                    <label for="name">民族：</label>
                                    <input type="text" class="form-control" id="nation">
                                </div>
                                <div class="form-group">
                                    <label for="name">政治面貌：</label>
                                    <input type="text" class="form-control" id="politics">
                                </div>

                                <div class="form-group">
                                    <label for="name">入学时间：</label>
                                    <input type="text" class="form-control" id="enrollmentTime">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group">
                                    <label for="name">身份证：</label>
                                    <input type="text" class="form-control" id="idCard">
                                </div>
                                <div class="form-group">
                                    <label for="name">联系电话：</label>
                                    <input type="text" class="form-control" id="phone">
                                </div>
                            </div>

                            <div class="row">

                                <div class="form-group">
                                    <label for="name">大学：</label>
                                    <input type="text" class="form-control" id="university">
                                </div>
                                <div class="form-group">
                                    <label for="name">学院：</label>
                                    <input type="text" class="form-control" id="college">
                                </div>

                                <div class="form-group">
                                    <label for="name">系别：</label>
                                    <input type="text" class="form-control" id="dept">
                                </div>

                                <div class="form-group">
                                    <label for="name">班级：</label>
                                    <input type="text" class="form-control" id="grade"">
                                </div>
                            </div>
                        </div>
                        <h4>家庭经济情况</h4>
                        <div style="margin-left: 20px;border: 2px">
                            <div class="row">

                                <div class="form-group">
                                    <label for="name">家庭户口：</label>
                                    <input type="text" class="form-control" id="residence">
                                </div>
                                <div class="form-group">
                                    <label for="name">家庭人口总数：</label>
                                    <input type="text" class="form-control" id="homeNum">
                                </div>

                                <div class="form-group">
                                    <label for="name">家庭总收入：</label>
                                    <input type="text" class="form-control" id="homeIncome">
                                </div>
                            </div>

                            <div class="row">

                                <div class="form-group">
                                    <label for="name">人均月收入：</label>
                                    <input type="text" class="form-control" id="monthIncome">
                                </div>
                                <div class="form-group">
                                    <label for="name">收入来源：</label>
                                    <input type="text" class="form-control" id="sourceIncome">
                                </div>
                            </div>

                            <div class="row">

                                <div class="form-group">
                                    <label for="name">家庭住址：</label>
                                    <input type="text" class="form-control" id="addr">
                                </div>
                                <div class="form-group">
                                    <label for="name">邮政编码：</label>
                                    <input type="text" class="form-control" id="postalCode">
                                </div>
                            </div>
                        </div>
                        <#--                        <h4>家庭成员情况</h4>-->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                    <textarea class="form-control" rows="4" id="achievement"
                                              placeholder="学习成绩"></textarea>
                                <textarea class="form-control" rows="4" id="reason" placeholder="申请理由"></textarea>

                            </div>
                        </div>
                        <#--                        <div class="row">-->
                        <#--                            <label class="col-sm-2 control-label" for="name">申请理由</label>-->
                        <#--                            <div class="col-sm-12">-->
                        <#--                                <textarea class="form-control" rows="3" id="reason"></textarea>-->
                        <#--                            </div>-->
                        <#--                        </div>-->
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
            var description = $("#description").val();
            var type = $("#select_type").val();
            var name = $("#name").val();
            var sex = $("#sex").val();
            var birthday = $("#birthday").val();
            var nation = $("#nation").val();
            var politics = $("#politics").val();
            var enrollmentTime = $("#enrollmentTime").val();
            var idCard = $("#idCard").val();
            var phone = $("#phone").val();
            var university = $("#university").val();
            var college = $("#college").val();
            var dept = $("#dept").val();
            var grade = $("#grade").val();
            var residence = $("#residence").val();
            var homeNum = $("#homeNum").val();
            var homeIncome = $("#homeIncome").val();
            var monthIncome = $("#monthIncome").val();
            var sourceIncome = $("#sourceIncome").val();
            var addr = $("#addr").val();
            var postalCode = $("#postalCode").val();
            var achievement = $("#achievement").val();
            var reason = $("#reason").val();
            $.post("/askLeave/add", {
                type: type,
                name: name,
                sex: sex,
                birthday: birthday,
                nation: nation,
                politics: politics,
                enrollmentTime: enrollmentTime,
                idCard: idCard,
                phone: phone,
                university: university,
                college: college,
                dept: dept,
                grade: grade,
                residence: residence,
                homeNum: homeNum,
                homeIncome: homeIncome,
                monthIncome: monthIncome,
                sourceIncome: sourceIncome,
                addr: addr,
                postalCode: postalCode,
                achievement: achievement,
                description: description,
                reason: reason,
                title: "申请助学金",
                status: '创建'
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

        function download(type, id) {
            var url = "/download";
            // 创建表单
            var formObj = document.createElement('form');
            formObj.action = url;
            formObj.method = 'get';
            formObj.style.display = 'none';
            // 创建input，主要是起传参作用
            var formItem = document.createElement('input');
            var formItem2 = document.createElement('input');
            formItem.value = type; // 传参的值
            formItem.name = 'fileType'; // 传参的字段名
            formItem2.value = id;
            formItem2.name = 'askId';
            // 插入到网页中
            formObj.appendChild(formItem);
            formObj.appendChild(formItem2);
            document.body.appendChild(formObj);
            formObj.submit(); // 发送请求
            document.body.removeChild(formObj); // 发送完清除掉
        }
    </script>
</@html>
