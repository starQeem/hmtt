<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#-- list集合遍历 -->
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>生日</td>
    </tr><br>
    <#list stus as stu>
       <#if stu.name = '咕咕'>
           <tr style="color: teal">
               <td>${stu_index + 1}</td>
               <td>${stu.money}</td>
               <td>${stu.name}</td>
               <td>${stu.age}</td><br>
           </tr>
           <#else >
           <tr style="color: red">
               <td>${stu_index + 1}</td>
               <td>${stu.money}</td>
               <td>${stu.name}</td>
               <td>${stu.age}</td><br>
           </tr>
       </#if>

    </#list>
</table><br>

<#-- Map 数据的展示 -->
<b>map数据的展示：</b>
<br/><br/>
<a href="###">方式一：通过map['keyname'].property</a><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
<br/>
<a href="###">方式二：通过map.keyname.property</a><br/>
输出stu2的学生信息：<br/>
姓名：${stuMap.stu2.name}<br/>
年龄：${stuMap.stu2.age}<br/>

<br/>
<a href="###">遍历map中两个学生信息：</a><br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap ? keys as key>
        <tr>
            <td>${key_index}</td>
            <td>${stuMap[key].name}</td>
            <td>${stuMap[key].age}</td>
            <td>${stuMap[key].money}</td>
        </tr>
    </#list>
</table>

</body>
</html>