var reactNameId;
$(function(){
	$.ajax({
            url:"/cea/getreact",
            type:"post",
            dataType:"json",
            data:"reactName="+"",
            async:false,
            success:function(data){
                setReactList(data);
            }
        });
    $('#gasReact').dblclick(function () {
        var selectReact = $(this).children('option:selected').val();
        $('#'+reactNameId).val(selectReact);
        $('#reactSelector').modal('toggle');
    });
    $('#condensateReact').dblclick(function () {
        var selectReact = $(this).children('option:selected').val();
        $('#'+reactNameId).val(selectReact);
        $('#reactSelector').modal('toggle');
    });
    $('#noneReactProduct').dblclick(function () {
        var selectReact = $(this).children('option:selected').val();
        $('#'+reactNameId).val(selectReact);
        $('#reactSelector').modal('toggle');
    });

    $('#gasProduct').dblclick(function () {
        var selectProduct = $(this).children('option:selected').val();
        $('#chosenProduct').append("<option value='"+selectProduct+"'>"+selectProduct+"</option>");
    });
    $('#condensateProduct').dblclick(function () {
        var selectProduct = $(this).children('option:selected').val();
        $('#chosenProduct').append("<option value='"+selectProduct+"'>"+selectProduct+"</option>");
    });
    $('#chosenProduct').dblclick(function () {
        var index=$('#chosenProduct').get(0).selectedIndex;
        // $('#chosenProduct option[index=' + "'"+index+"'" + ']').remove();
        // $('#chosenProduct').options.remove(index);
        document.getElementById("chosenProduct").options.remove(index)
    });

})
function showReactSelector(reactNameText){
    reactNameId = reactNameText;
	$('#reactSelector').modal('toggle');
}

function setReactList(data){
    $('#gasReact').empty();
    $('#condensateReact').empty();
    $('#noneReactProduct').empty();
	$.each(data, function (key, value) {
        switch(key)
        {
            case "b1":
                $.each(value, function (index,val) {
                    $('#gasReact').append("<option value='"+val+"'>"+val+"</option>");
                });
                break;
            case "b2":
                $.each(value, function (index,val) {
                    $('#condensateReact').append("<option value='"+val+"'>"+val+"</option>");
                });
                break;
            default:
                $.each(value, function (index,val) {
                    $('#noneReactProduct').append("<option value='"+val+"'>"+val+"</option>");
                });
        }
    });
}

function searchReact() {
    var search = $('#searchText').val();
    $.ajax({
        url:"/cea/getreact",
        type:"post",
        dataType:"json",
        data: {partialName: search},
        async:false,
        success:function(data){
            setReactList(data);
        }
    });
}

function generateProduct() {
    var form = new FormData($("#reactForm")[0]);
    $.ajax({
        url:"/cea/product",
        type:"post",
        data:form,
        cache: false,
        processData: false,
        contentType: false,
        success:function(rtn){
            setProductList(rtn)
            $('html, body').animate({
                scrollTop: $("#product").offset().top
            }, "fast");

        },
        error:function(e){
            alert("网络错误，请重试！！");
        }
    });
}

function setProductList(data){
    $('#gasProduct').empty();
    $('#condensateProduct').empty();
    $('#chosenProduct').empty();
    $.each(data, function (key, value) {
        switch(key)
        {
            case "b1":
                $.each(value, function (index,val) {
                    $('#gasProduct').append("<option value='"+val+"'>"+val+"</option>");
                });
                break;
            case "b2":
                $.each(value, function (index,val) {
                    $('#condensateProduct').append("<option value='"+val+"'>"+val+"</option>");
                });
                break;
            default:
                ;
        }
    });
}

function generateThermalResult() {
    var t1 = $('#reactForm').serializeArray();
    var t2 = new Array();
    $("#chosenProduct"+" option").each(function(){
        //遍历所有option
        var value = $(this).val();   //获取option值
        var text = $(this).text();
        if(text!=''){
            var map = {};
            map["reactParameters.productList"] = value;
            t2.push(map);
        }
    });
    var t3 = $('#rocketForm').serializeArray();
    var temp = t1.concat(t2);
    var data = temp.concat(t3);

    $.ajax({
        url:"/cea/result",
        type:"post",
        dataType:"json",
        data:data,
        async:false,
        success:function(data){
            showResult(data);
            $('html, body').animate({
                scrollTop: $("#result").offset().top
            }, "fast");
        }
    });

}

function showResult(data) {
    $.each(data, function (key, values) {
        if (key.indexOf("eq_") >=0 ) {
            var trStr = "<tr>";
            $.each(values, function (index, val) {
                if (values.length == 4){
                    if (index == 1){
                        trStr += "<th>"+" "+"</th>";
                    }
                }
                trStr += "<th>"+val+"</th>";
            });
            trStr +="</tr>"
            $('#eqTable').append(trStr);
        }
        if (key.indexOf("fr_") >=0 ) {
            var trStr = "<tr>";
            $.each(values, function (index, val) {
                if (values.length == 4){
                    if (index == 1){
                        trStr += "<th>"+" "+"</th>";
                    }
                }
                trStr += "<th>"+val+"</th>";
            });
            trStr +="</tr>"
            $('#frTable').append(trStr);
        }
    })
}
