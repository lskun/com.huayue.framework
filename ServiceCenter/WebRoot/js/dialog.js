function ShowMask()
{
    with (GetE('a_mask').style)
    {
        position = 'absolute';
        zIndex = 99;
        backgroundColor = '#333333';
        filter = 'alpha(opacity=90)';
        opacity = '0.9';
        width = document.documentElement.scrollWidth + 'px';
        height = document.documentElement.scrollHeight + 'px';
        top = '0px';
        left = '0px';
        display = '';
    }
}

function ShowDialog(shtml, title, width, height, hideClose)
{
    var dlg = GetE('a_dialog');
    GetE('a_dialog_cont').innerHTML = shtml;
    GetE('a_dialog_title').innerHTML = title;
    
    dlg.style.top = (document.documentElement.scrollTop + document.documentElement.clientHeight / 2 - height / 2) + 'px';
    dlg.style.width = width + 'px';
    dlg.style.left = (document.documentElement.clientWidth / 2 - width / 2) + 'px';
    dlg.style.display = '';
    
    if (hideClose) GetE('a_dialog_close').style.visibility = 'hidden';
    
    ShowMask();
}

function HideMask()
{
    GetE('a_mask').style.display = 'none';
}

function CloseDialog()
{
    var dlg = GetE('a_dialog');
    if (dlg) dlg.style.display = 'none';
    
    HideMask();
}
