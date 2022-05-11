function forItem(){
    loadAllItem();
    generateItemCode();
}


function generateItemCode() {

    $.ajax({
        url:"item",
        method:"GET",
        success(resp){

            try {
                let lastItemCode = resp.data[resp.data.length - 1].code;
                let newItemCode = parseInt(lastItemCode.substring(1, 4)) + 1;

                if (newItemCode < 10) {
                    $("#txtItemCode").val("I00" + newItemCode);
                } else if (newItemCode < 100) {
                    $("#txtItemCode").val("I0" + newItemCode);
                } else {
                    $("#txtItemCode").val("I" + newItemCode);
                }
            }catch (e) {
                $("#txtItemCode").val("I001");
            }



        }
    });

}

function saveItem(){
    var data = $("#itemForm").serialize(); // return query string of form with name:type-value


    $.ajax({
        url: "item",
        method: "POST",
        data: data,
        success(res) {
            if (res.status == 200) {
                alert(res.data);
                // loadAllCustomer();
                // generateCustomerID();
            } else {
                alert(res.data);
            }
        },
        error (ob, textStatus, error) {
            alert(textStatus);


        }
    });
}

function loadAllItem(){
    $("#itemTable").empty();
    $.ajax({
        url:"item",
        method:"GET",
        success(resp) {
            console.log(resp.data);
            for (var i of resp.data) {
                var row = `<tr><td>${i.code}</td><td>${i.name}</td><td>${i.qty}</td><td>${i.unitPrice}</td></tr>`;

                $("#itemTable").append(row);


                $("#itemTable>tr").click(function () {


                    $("#txtItemCode").val($(this).children(":eq(0)").text());
                    $("#txtItemName").val($(this).children(":eq(1)").text());
                    $("#txtQty").val($(this).children(":eq(2)").text());
                    $("#txtPrice").val($(this).children(":eq(3)").text());


                });
            }
        }
    });
}

 $("#btn-item-save").click(function (){
    saveItem();
 });
$("#btn-customer-clear-feild").click(function (){
    clearAll();
});


$("#btn-delete-customer").click(function (){
    $.ajax({
        url:"customer?customerID="+ $("#txtCusID").val(),
        method:"DELETE",

        success(resp) {
            if (resp.status == 200) {
                loadAllCustomer();
                alert(resp.message);
                clearAll();
            } else if (resp.status == 400) {
                alert(resp.message);
            } else {
                alert(resp.data);
            }
        }
    });
});

$("#btn-update-customer").click(function (){
    var cusOb ={
        "id":$("#txtCusID").val(),
        "name":$("#txtCusName").val(),
        "address":$("#txtCusAddress").val(),
        "salary":$("#txtCusSalary").val()
    }


    $.ajax({
        url:"customer",
        method:"PUT",
        contentType:"application/json",
        data:JSON.stringify(cusOb),
        success(resp){
            if(resp.status==200){
                loadAllCustomer();
                alert(resp.message);
            }else {
                alert(resp.data);

            }
        }
    });
});


$("#btn-customer-search").click(function (){
    var custID= $("#txtCustomerSearch").val();
    $.ajax({
        url:"customer",
        method:"Get",
        success(resp){
            for (var i of resp.data) {
                if(custID==i.id){
                    $("#txtCusName").val(i.name);
                    $("#txtCusAddress").val(i.address);
                    $("#txtCusSalary").val(i.salary);
                    $("#txtCusID").val(i.id);
                }
            }
        }
    });
});