<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
    <base href="<%=basePath%>">

    <link href="bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet" />
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->

    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>

    <!--CSS-->
    <style type="text/css">

        .border{
            border:2px solid;
            border-top-color:#FFFFFF;
            border-bottom-color:#F7F8F9;
            border-left-color:#FFFFFF;
            border-right-color:#FFFFFF;
        }
        .float{
            float:right;
            margin-top:10px;
            text-align:center;
            border-radius:15px;
        }
        .renmzy{
            height:20px;
            width:150px;
            float:left;
            margin-left:25px;
            border:1px solid;
            border-radius:10px;
            border-color:#F75D47;
            color:#F75D47;
            text-align:center;
            font-size:13px;
            margin-top:8px;
        }

    </style>

    <%--<script type="text/javascript">
        $(document).ready(function(){
            var ajaxUrl="/resource/getResourceComments?rid="+"3";
            $.ajax({
                type:"post",
                url:ajaxUrl,
                //data:bokeid,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    var blogComments="";
                    for(var c=0;c<data.length;c++){
                        blogComments+='';
                        blogComments+='<div style="">';
                        blogComments+='<div style="margin-top: 5px">';
                        blogComments+='<a href="#">'+data[c].nickname+'</a>';
                        blogComments+='</div>';
                        blogComments+='</div>';
                        blogComments+='<h5 style="margin: 25px 10px 10px 50px">'+data[c].comment+'</h5>';
                    }
                    $('#getpl').append(blogComments);
                },
                error: function(data) {
                    alert("error:");
                }
            });
        });
    </script>--%>
    <script type="text/javascript">
        function conrid() {
            var urll='/resource/houseResource';
            var daa=$("#houseForm").serialize();
            $.ajax({
                async: false,
                type: "POST",
                url:urll,
                data:daa,
                dataType: "text",
                success: function (data) {
                    alert(data);
                },
                error: function (data) {
                    alert("资源收藏失败");
                }
            })
        };
        function toassess() {
            var urll='/resource/assess';
            var daa=$("#assessForm").serialize();
            $.ajax({
                async: false,
                type: "POST",
                url:urll,
                data:daa,
                dataType: "text",
                success: function (data) {
                    alert(data);
                    var blogComments="";
                    blogComments+='<ul><hr><li id="getpl"><br>' +
                        '<div style=""><br>' +
                        '                            <div style="float: left"><a class="icon-observer" href="#" style="background-image: url(\'img/3_qq.jpg\')"></a></div><br>' +
                        '                            <div style="margin-top: 5px"><br>' +
                        '                                <a href="#">'+data.slice(3)+'</a><br>' +
                        '                            </div><br>' +
                        '</div><br>' +
                        '<h5 style="margin: 25px 10px 10px 50px">'+data.substring(1,5)+'</h5><br>' +
                        '</li></hr></ul>'
                    $('#addAssess').append(blogComments);
                },
                error: function (data) {
                    alert("没有评论权限");
                }
            })
        };
    </script>
</head>
<body  style="background-color:#F7F8F9">
<c:if test="${requestScope.resouce eq null}">
    <c:redirect url="/index.jsp"></c:redirect>
</c:if>
<c:if test="${requestScope.downloapath != null}">
    <c:redirect url="/${requestScope.downloapath}"></c:redirect>
</c:if>
<div class="container-fluid">
    <!--	导航栏-->
    <%@ include file="top.jsp"%>
    <!--	导航栏结束-->
    <!--二级导航开始-->

    <div class="row"  style="margin-top: -1.2%; font-size:16px;background-color:#EAEBEC; min-width:1024px;" >

        <ul class="nav nav-tabs"  style="margin-left:15%;" >
            <li ><a href="xiazai.jsp">下载首页</a></li>
            <li ><a href="myresource.jsp">我的资源</a></li>
            <li ><a href="upload.jsp">上传资源赚积分</a></li>
            <li ><a href="myresource.jsp">已下载</a></li>
            <li ><a href="myresource.jsp">我的收藏</a></li>
            <%--<c:if test="${sessionScope.loginresult.isadmin eq '1'}">--%>
            <c:if test="${sessionScope.loginresult.isadmin eq '1' && not empty reportRid && !(reportRid eq null)}">
                <%--<li style="margin-left: 5%"><a href="/blog/mGetAllReportBlog">返回举报页</a></li>--%>
                <li><a href="javascript:deleteResource('${requestScope.resouce.path}')">删除</a></li>
                <li><a style="cursor: default">举报原因：${reportRReason}</a></li>
            </c:if>
        </ul>

    </div>
    <!--二级导航结束-->
<script>
    function deleteResource(url) {
        var userChoice=window.confirm("您确认要去除这个资源吗？");
        var a="/resource/mDeleteResourceForReport/${resourceid}";
        if(userChoice){
            $("loadGif").show();
            $.get(a,function (data) {
                $("loadGif").hide();
                if(data=="success"){
                    location.href="/blog/mGetAllReportBlog";
                }else {
                    alert("未知错误，请再试一次！！")
                }
            })

        }
    }
</script>



    <div class="row well" style="margin-left: 10%;margin-right: 10%; min-width:1024px;">

        <div class="col-xs-8">
            <!--左第一大块div-->
            <div  style=" height:260px;background-color:#FFFFFF;">


                <!--第一个div-->
                <div class="border" style="height:150px;">
                    <div style="height:85px; width:800px; margin-left:20px;">
                        <div style="height:48px; width:42px; float:left; margin-top:15px ">
                            <img src="img/2.svg">
                        </div>
                        <div style="height:20px; width:420px; float:left; margin-top:15px; margin-left:40px;  font-size:20px ; color:#000000;">
                            <a>${requestScope.resouce.name}</a>

                        </div>
                        <div style="height:30px; width:700px; float:left;margin-top:12px; margin-left:40px;font-size:14px;">
                            <div style="width:250px; height:30px;  float:left">
                                <span> ${requestScope.resouce.time}  上传</span>
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;大小：<em>308KB</em></span>
                            </div>
                            <c:forEach items="${requestScope.resouce.resourceTags}" var="tag">
                                <div style="height:23px;min-width:60px;float:left;margin-left:30px;border:1px solid;;border-radius:10px; border-color:#F75D47; color:#F75D47;text-align:center;">
                                    <a href="#">${tag.rtag}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>


                    <div style=" background-color:#F5F5F5; margin-left:20px">
                        <div class="row-fluid">
                            <div class="span12" style="width:600px; ">
                                <p>${requestScope.resouce.introduce}</p>
                            </div>
                        </div>
                    </div>
                </div>


                <!--第二个div-->
                <div class="border" style=" height:45px;">
                    <div style=" height:25px; width:600px;  margin-top:10px; margin-left:20px">
                  <span>
                   下载次数：${requestScope.downNum}&nbsp;&nbsp;&nbsp;&nbsp;
                  </span>
                        <span> 综合评分：<em>7.9</em></span>
                        <div style="float:right; width:250px;">
                                <form id="houseForm" style="float:right; width:100px;" >
                                    <c:if test="${sessionScope.loginresult !=null}">
                                        <input type="hidden" name="uid" value="${sessionScope.loginresult.uid}">
                                        <input type="hidden" name="rid" value="${requestScope.resouce.rid}">
                                        <button type="button" onclick="conrid()" ><img src="img/sc.jpg">&nbsp;收藏</button>
                                    </c:if>
                                    <c:if test="${sessionScope.loginresult eq null}">
                                        <button type="button" onclick="tishilogin()" ><img src="img/sc.jpg">&nbsp;收藏</button>
                                    </c:if>
                                </form>
                            <c:if test="${sessionScope.loginresult !=null}">
                                <a data-toggle="modal" data-target="#modal-container-830220" ><img src="img/jb.jpg" >&nbsp;举报</a><input type="hidden" value="${isReportOk}"/>
                            </c:if>
                            <c:if test="${sessionScope.loginresult eq null}">
                                <a onclick="tishilogin()" ><img src="img/jb.jpg" >&nbsp;举报</a><input type="hidden" value="${isReportOk}"/>
                            </c:if>
                            <!-- 模态框（Modal） -->
                            <!-- Modal -->
                            <div class="modal fade" id="modal-container-830220" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="margin-top: 20%">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                            <h4 class="modal-title" id="myModalLabel">举报原因</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!--								文本域-->
                                            <form >
                                                <input name="rid" type="hidden" value="${requestScope.resouce.rid}" readonly="readonly"/>
                                                <input name="uid" type="hidden" value="${sessionScope.loginresult.uid}" readonly="readonly"/>
                                                <textarea id="reason" name="reason" class="form-control" rows="3"></textarea><br/>
                                                <input id="reportResource" style="margin-left: 92%;"  type="button" value="提交"/>
                                            </form>
                                            <!--								-->

                                        </div>
                                       <%-- <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                            <button id="submitAdd" type="button" class="btn btn-primary">提交</button>
                                        </div>--%>
                                    </div>
                                </div>
                            </div>
                            <!-- /.modal-content -->
                        </div>
                    </div>

                </div>



                <!--第三个div-->
                <div style=" height:50px;">
                    <div  style=" height:28px; width:200px; padding:15px 20px;  float:left">
                        <label style="font-size:16px">
                            所需:
                            <em style="color:#FACC16;font-size:22px">${requestScope.resouce.scoring}</em>
                            积分/C币
                        </label>
                    </div>



                    <div class="float" style=" height:40px; width:140px;  background-color: #E7567F; margin-right:40px;" >
                        <a href="#" > <div style="height:30px; margin-top:5px"><img src="img/vip.jpg">开通VIP</div></a>
                    </div>

                    <div class="float" style="height:38px;width:120px;margin-right:20px;border:1px solid;border-color:#95E2E8;">
                        <%--<a  href="assess.jsp" onclick="downLoad()" >
                            <div style="height:20px; margin-top:8px">立即下载</div>
                        </a>--%>
                            <c:if test="${sessionScope.loginresult != null}">
                                <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                                    立即下载
                                </button>
                            </c:if>
                            <c:if test="${sessionScope.loginresult eq null}">
                                <button type="button" class="btn btn-primary btn-lg" onclick="tishilogin()">
                                    立即下载
                                </button>
                            </c:if>

                    </div>

                </div>
            </div>
            <!--下载确认模态框-->

            <c:if test="${sessionScope.loginresult !=null}">
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">确认下载</h4>
                        </div>
                        <div class="modal-body">
                            本次下载将扣取${requestScope.resouce.scoring}积分,你现有${sessionScope.loginresult.score}积分
                            <br/><font color="red">${requestScope.isDowned}</font>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <c:if test="${sessionScope.loginresult.score - requestScope.resouce.scoring > 0}">
                                <a href="/resource/downLoadResource?rid=${requestScope.resouce.rid}&downUserid=${sessionScope.loginresult.uid}" target="_blank">
                                    <button type="button" class="btn btn-primary">确定下载</button>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <!--下载确认模态框结束-->
            </c:if>

            <!--左第一大块div结束-->

            <!--左第二个div-->
            <div  style="height:55px; margin-top:10px; background-color:#FFFFFF">
                <div class="span12" style="background-color:#A29E9E;padding: 25px">
                    <!-- Button trigger modal -->
                    有疑问？就说一说
                    <c:if test="${sessionScope.loginresult != null}">
                        <button type="button"  class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModa">
                            我要评论
                        </button>
                    </c:if>
                    <c:if test="${sessionScope.loginresult eq null}">
                        <button type="button"  class="btn btn-primary btn-xs" onclick="tishilogin()">
                            我要评论
                        </button>
                    </c:if>

                    <!-- Modal -->
                    <div class="modal fade" id="myModa" tabindex="-1" role="dialog" aria-labelledby="myModalLabe" style="margin-top: 20%">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabe">我的评论</h4>
                                </div>
                                <form id="assessForm" action="/resource/assess" method="post">
                                        <input type="hidden" name="uid" value="${sessionScope.loginresult.uid}"><br>
                                        <input type="hidden" name="rid" value="${requestScope.resouce.rid}"><br>
                                        <textarea id="name" name="comment" class="form-control" rows="3"></textarea>
                                        评分:<select name="star">
                                        <c:forEach var="a" begin="1" end="10">
                                            <option value="${a}">${a}分</option>
                                        </c:forEach>
                                    </select><br/>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                        <button type="button" class="btn btn-primary" onclick="toassess()" value="discuss">评论</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--资源评论输出div-->
            <c:if test="${requestScope.rcomments !=null}">

            <div id="addAssess" style="height:400px; margin-top:10px; background-color:#FFFFFF">
                <ul>
                    <hr >

                    <c:forEach items="${requestScope.rcomments}" var="rc">
                    <li id="getpl">
                        <div style="">
                            <div style="float: left"><a class="icon-observer" href="#" style="background-image: url('img/3_qq.jpg')"></a></div>
                            <div style="margin-top: 5px">
                                <a href="#">${rc.user.nickname}</a>
                            </div>
                        </div>
                        <h5 style="margin: 25px 10px 10px 50px">${rc.comment}</h5>
                    </li>
                    </c:forEach>

                    <hr>
                </ul>
            </div>
            </c:if>

        </div>

            <div class="col-xs-4 well">
            <!--右边第一块上传资源的div-->
            <div style="height:50px; width:300px; background-color:#E33F3F">
                <div class="row-fluid">
                    <div class="span12"  style=" height:30px;; font-size:18px;text-align:center;padding:11px;">
                        <a href="/upload.jsp" ><img src="img/shangc.jpg">&nbsp;<font color="#FFFFFF">上传资源</font></a>
                    </div>
                </div>
            </div>
            <!--右边第一块上传资源的div结束-->

            <!--右边第二块上传资源的div-->
            <div style="height:73px; width:300px; background-color:#FFFFFF; margin-top:15px;">
                <!--最左边-->
                <div style="height:50px; width:50px; float:left; margin-top:11px; margin-left:15px">
                    <a herf="#"><img height="50px" width="50px" src="${requestScope.pushUser.picturepath}" /></a>
                </div>
                <!--最左边-->
                <!--中间-->
                <div style="height:50px; width:80px; float:left; margin-top:11px; margin-left:10px;">
                    <div style="height:20px; margin-top:6px; font-size:14px">
                        <a href="#">${requestScope.pushUser.nickname}</a>
                    </div>
                    <div style="height:20px; width: 20px; margin-top:0px; font-size:14px">
                        <a href="#"><img src="img/xz.jpg" /></a>
                    </div>
                </div>
                <!--中间-->

                <!--最右边-->
                <div style="height:50px; width:100px; float:left; margin-top:11px; margin-left:15px">
                    <div style="height:20px;width:60px; margin-top:6px; font-size:14px;border:2px solid; border-radius:15px; border-color:#F50105;text-align:center;">
                        <c:if test="${sessionScope.loginresult eq null}">
                            <a onclick="tishilogin()">关注</a>
                        </c:if>
                        <c:if test="${sessionScope.loginresult != null}">
                            <a >关注</a>
                        </c:if>
                    </div>
                    <div style="height:25px; margin-top:5px; font-size:14px">
                        <c:if test="${sessionScope.loginresult eq null}">
                            <a onclick="tishilogin()">查看TA的资源</a>
                        </c:if>
                        <c:if test="${sessionScope.loginresult != null}">
                            <a >查看TA的资源</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <!--最右边-->
            <!--右边第二块上传资源的div结束-->


            <!--右边第三块上传资源的div-->
            <div style=" width:300px; background-color:#FFFFFF; margin-top:15px; font-size:18px;">
                <div class="container-fluid">
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="tabbable" >
                                <ul class="nav nav-tabs">
                                    <li>
                                        <a href="#" data-toggle="tab">热门资源标签</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                    <div class="renmzy" ><a href="#"> IP地址.......s</a> </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--右边第三块上传资源的div结束-->

        </div>
    </div>
</div>
</div>
<div id="loadGif"  style="display: none;margin: auto;width: 50px;height: auto;position: fixed;left: 45%;top: 35%;z-index: 10;border-radius: 25px">
    <img alt="加载。。。" src="img/Rload.gif" style="width: 100%;height: auto;border-radius: 25px">
</div>
</body>
<script type="text/javascript">

    $(document).ready(function () {
        $("#reportResource").click(function () {
            $.get("/resource/reportResource?rid=${requestScope.resouce.rid}&uid=${sessionScope.loginresult.uid}&reason="+$('#reason').val(),function (data,status) {
                $("#modal-container-830220").modal('hide');
                alert("举报成功");
            })
        })
    });


</script>
</html>
