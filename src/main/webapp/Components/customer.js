/**
*
*/

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});




// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

// Form validation-------------------
var status = validateItemForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}



// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "CustomerAPI",
type : type,
data : $("#formItem").serialize(),
dataType : "text",

complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});




function onItemSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}

$("#hidItemIDSave").val("");
$("#formItem")[0].reset();
}





// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
$("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
$("#customerEmail").val($(this).closest("tr").find('td:eq(1)').text());
$("#cusAge").val($(this).closest("tr").find('td:eq(2)').text());
$("#password").val($(this).closest("tr").find('td:eq(3)').text());
$("#phone").val($(this).closest("tr").find('td:eq(4)').text());
$("#nic").val($(this).closest("tr").find('td:eq(5)').text());
});




// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#customerName").val().trim() == "")
{
return "Insert Customer Name.";
}
// NAME
if ($("#customerEmail").val().trim() == "")
{
return "Insert Customer Email.";
}

// PRICE-------------------------------
if ($("#cusAge").val().trim() == "")
{
return "Insert Customer Age.";
}

if ($("#password").val().trim() == "")
{
return "Insert password .";
}

if ($("#phone").val().trim() == "")
{
return "Insert phone NO.";
}


// DESCRIPTION------------------------
if ($("#nic").val().trim() == "")
{
return "Insert  nic.";
}
return true;
}



//DELETE
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "CustomerAPI",
type : "DELETE",
data : "customerNumber=" + $(this).data("customernumber"),
dataType : "text",
complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});




function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divItemsGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}