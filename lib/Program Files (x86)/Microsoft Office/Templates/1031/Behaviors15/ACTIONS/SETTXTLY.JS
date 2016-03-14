//////////////////////////////////////////////////////////

function initDialog()
{
	var nNoName=1;
	var sOptionText;
	var sValue='';
	var elemDiv;
	var elemText;
	var nFormNumber = 0;
	var fpdoc = FrontPage.ActiveDocument;
	var pos;

	var aoDivs = fpdoc.all.tags("div");

	for(i=0; i < aoDivs.length; i++)
	{
		elemDiv = aoDivs(i);
		
		if( !isValidElmt( fpdoc, elemDiv ) )
			continue;
	
		pos = elemDiv.style.Position;
		if ( pos.toLowerCase() == "absolute")
		{
			oOption=document.createElement("option");
			sOptionText = L_DIV_ListBox + ' ';
			sValue = '';
		
			if (elemDiv.id != '')
			{
				sOptionText+= '\"' + elemDiv.id +'\"';
				sValue = elemDiv.id;
			}
			else
			{
				sOptionText += '\"' + L_Unnamed_ListBox + nNoName + '\"';
				sValue = 'unnamed' + nNoName;
				nNoName++;
			}
			sValue += ', ';
			sValue += elemDiv.tagname;
			oOption.text = sOptionText;
			oOption.value = sValue;
			theForm.LayerName.options.add(oOption);
		}//end if position==absolute
	}
	
	var aoLayers = fpdoc.all.tags("layer");

	for(i=0; i < aoLayers.Length; i++)
	{
		elemDiv = aoLayers(i);
		
		if( !isValidElmt( fpdoc, elemDiv ) )
			continue;
		
		pos = elemDiv.style.Position;
		if ( pos.toLowerCase() == "absolute")
		{
			oOption=document.createElement("option");
			sOptionText = L_LAYER_ListBox + ' ';
			sValue = '';
		
			if (elemDiv.id != '')
			{
				sOptionText+= '\"' + elemDiv.id +'\"';
				sValue = elemDiv.id;
			}
			else
			{
				if (elemDiv.name != '')
				{
					sOptionText+= '\"' + elemDiv.name + '\"';
					sValue = elemDiv.name;
				}
				else
				{
					sOptionText += '\"' + L_Unnamed_ListBox + nNoName + '\"';
					sValue = 'unnamed' + nNoName;
					nNoName++;
				}
			}
			sValue += ', ';
			sValue += elemDiv.tagname;
			oOption.text = sOptionText;
			oOption.value = sValue;
			theForm.LayerName.options.add(oOption);
		}//end if position==absolute
	}
	
	var aoSpans = fpdoc.all.tags("span");
	
	for(i=0; i < aoSpans.Length; i++)
	{
		elemDiv = aoSpans(i);
		
		if( !isValidElmt( fpdoc, elemDiv ) )
			continue;
		
		pos = elemDiv.style.Position;
		if ( pos.toLowerCase() == "absolute")
		{
			oOption=document.createElement("option");
			sOptionText = L_SPAN_ListBox + ' ';
			sValue = '';
		
			if (elemDiv.id != '')
			{
				sOptionText+= '\"' + elemDiv.id +'\"';
				sValue = elemDiv.id;
			}
			else
			{
				sOptionText += '\"' + L_Unnamed_ListBox + nNoName + '\"';
				sValue = 'unnamed' + nNoName;
				nNoName++;
			}
			sValue += ', ';
			sValue += elemDiv.tagname;
			oOption.text = sOptionText;
			oOption.value = sValue;
			theForm.LayerName.options.add(oOption);
		}//end if position==absolute
	}
	if(theForm.LayerName.options(0) != null)
		theForm.LayerName.options(0).selected=true;
		
	safeFocus(theForm.LayerName)
}

//////////////////////////////////////////////////////////

function FP_setLayerText(id,txt) {//v1.0
 var d=document,el=FP_getObjectByID(id);		
 if(el.innerHTML!=null)el.innerHTML=txt; 
}

//////////////////////////////////////////////////////////

function getScript()
{
	return "FP_setLayerText,FP_getObjectByID";
}

//////////////////////////////////////////////////////////

function getScriptCall()
{
	var sCallString;
	var sLayerText;
	var sLayerID;
	var eLayer;
	var nNoName = 0;
	var fpdoc = FrontPage.ActiveDocument;
	var pos;
	sLayerText = String( theForm.Message.value );
	sLayerText = sLayerText.substr( 0, 10000 ); 	// limit text to 10000 characters
	sLayerText=encodeTextArea(sLayerText);

	sValue = theForm.LayerName.children(theForm.LayerName.selectedIndex).value;
	sLayerID = sValue.substr(0,sValue.indexOf(', '));
	sTagName = sValue.substring(sValue.indexOf(', ')+2,sValue.length);
	if (sLayerID.indexOf('unnamed')>=0)
	{
		sLayerID = sLayerID.substring(7,sLayerID.length);
		nNoName = 1;
		for(i=0; i<fpdoc.all.tags("div").Length; i++)
		{
			elemDiv = fpdoc.all.tags("div").item(i);
			pos = elemDiv.style.Position;
			if ( pos.toLowerCase() == "absolute")
			{
				if (elemDiv.id == '')
				{
					if(nNoName == sLayerID)
						eLayer = elemDiv;
					nNoName++;
				}
			}
		}
		for(i=0; i<fpdoc.all.tags("layer").Length; i++)
		{
			elemDiv = fpdoc.all.tags("layer").item(i);
			pos = elemDiv.style.Position;
			if ( pos.toLowerCase() == "absolute")
			{
				if ((elemDiv.id == '') && (elemDiv.name == ''))
				{
					if(nNoName == sLayerID)
						eLayer = elemDiv;
					nNoName++;
				}
			}
		}
		for(i=0; i<fpdoc.all.tags("span").Length; i++)
		{
			elemDiv = fpdoc.all.tags("span").item(i);
			pos = elemDiv.style.Position;
			if ( pos.toLowerCase() == "absolute")
			{	
				if (elemDiv.id == '')
				{
					if(nNoName == sLayerID)
						eLayer = elemDiv;
					nNoName++;
				}
			}
		}
		if (eLayer==null)
			alert(L_NOTAG_ErrorMessage);
		
	}
	else
		eLayer = fpdoc.all.tags(sTagName).item(sLayerID);
	if (eLayer != null)
	{
		if (eLayer.id == '')
			eLayer.id = generateUniqueID( fpdoc );
		sLayerID = eLayer.id;
	}
	else
		alert(L_NOTAG_ErrorMessage);
	sCallString='FP_setLayerText(';
	sCallString += FP_ADJECTIVE_ID + '\'' + sLayerID;
	sCallString += '\', \'' + sLayerText;
	sCallString += '\')';
	return sCallString;
}

//////////////////////////////////////////////////////////

function isScriptEnabled( fpdoc )
{
	// script available when Layers in document
	var elemcoll;
	var style;	
	elemcoll = fpdoc.all.tags("div");
	for (i = 0; i< elemcoll.Length; i++)
	{
		if( !isValidElmt( fpdoc, elemcoll(i) ) )
			continue;
				
	    style = elemcoll(i).style;
            pos = style.Position;
	    if ( pos.toLowerCase() == "absolute")
			return true;
	}
	elemcoll = fpdoc.all.tags("layer")
	for (i = 0; i< elemcoll.Length; i++)
	{
		if( !isValidElmt( fpdoc, elemcoll(i) ) )
			continue;

	    style = elemcoll(i).style;
            pos = style.Position;
	    if ( pos.toLowerCase() == "absolute")
			return true;
	}
	elemcoll = fpdoc.all.tags("span")
	for (i = 0; i< elemcoll.Length; i++)
	{
		if( !isValidElmt( fpdoc, elemcoll(i) ) )
			continue;

	    style = elemcoll(i).style;
            pos = style.Position;
	    if ( pos.toLowerCase() == "absolute")
			return true;
	}
	return false;
}

//////////////////////////////////////////////////////////


function initPropertyDialog(strScriptCall)
{
	//The arguments should be (id, txt)
	var aArguments = extractArgumentsFromScriptCall( strScriptCall );
	var bFound=false;
	nArgs= aArguments.length;
	if (nArgs != 0)
	{
		var id=aArguments[0].strArgument;
		id=trimQuotes(id);
		oLayerSelector=document.all.tags("select").item("LayerName");
		for(i=0; i<oLayerSelector.children.length; i++)
		{
			sChildText = oLayerSelector.children(i).text;
			if (sChildText.indexOf(id)>=0)
			{
				oLayerSelector.children(i).selected=true;
				bFound=true;
			}
			else
				oLayerSelector.children(i).selected=false;
		}
		if (!bFound)
			alert(L_FIELDNOTFOUND_ErrorMessage);
		var sMessage = aArguments[1].strArgument;
		sMessage=trimQuotes(sMessage);
		sMessage=decodeTextArea(sMessage);
		theForm.Message.value = sMessage;
	}
}

//////////////////////////////////////////////////////////

function Validate()
{
	if (theForm.Message.value=="")
	{
		alert(L_NOMESSAGE_ErrorMessage);
		theForm.Message.focus();
		return;
	}
	if (theForm.LayerName.children.length == 0)
	{
		alert(L_NOVALIDFIELDS_ErrorMessage);
		return;
	}
	if(isTextBoxTextValid(theForm.Message.value))
	{
		window.returnValue = RETURN_OK;
		window.close();
	}
	else
		theForm.Message.focus();
}

//////////////////////////////////////////////////////////

function CancelAction()
{
	window.returnValue = RETURN_CANCEL;	
	window.close();
}


// SIG // Begin signature block
// SIG // MIIatAYJKoZIhvcNAQcCoIIapTCCGqECAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFMg7QjEH2T+f
// SIG // IevB1mTD5IRYMxXDoIIVkzCCA8QwggMtoAMCAQICEEe/
// SIG // GZXfjVJGQ/fbbUgNMaQwDQYJKoZIhvcNAQEFBQAwgYsx
// SIG // CzAJBgNVBAYTAlpBMRUwEwYDVQQIEwxXZXN0ZXJuIENh
// SIG // cGUxFDASBgNVBAcTC0R1cmJhbnZpbGxlMQ8wDQYDVQQK
// SIG // EwZUaGF3dGUxHTAbBgNVBAsTFFRoYXd0ZSBDZXJ0aWZp
// SIG // Y2F0aW9uMR8wHQYDVQQDExZUaGF3dGUgVGltZXN0YW1w
// SIG // aW5nIENBMB4XDTAzMTIwNDAwMDAwMFoXDTEzMTIwMzIz
// SIG // NTk1OVowUzELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZl
// SIG // cmlTaWduLCBJbmMuMSswKQYDVQQDEyJWZXJpU2lnbiBU
// SIG // aW1lIFN0YW1waW5nIFNlcnZpY2VzIENBMIIBIjANBgkq
// SIG // hkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqcqypMzNIK8K
// SIG // fYmsh3XwtE7x38EPv2dhvaNkHNq7+cozq4QwiVh+jNtr
// SIG // 3TaeD7/R7Hjyd6Z+bzy/k68Numj0bJTKvVItq0g99bbV
// SIG // XV8bAp/6L2sepPejmqYayALhf0xS4w5g7EAcfrkN3j/H
// SIG // tN+HvV96ajEuA5mBE6hHIM4xcw1XLc14NDOVEpkSud5o
// SIG // L6rm48KKjCrDiyGHZr2DWFdvdb88qiaHXcoQFTyfhOpU
// SIG // wQpuxP7FSt25BxGXInzbPifRHnjsnzHJ8eYiGdvEs0dD
// SIG // mhpfoB6Q5F717nzxfatiAY/1TQve0CJWqJXNroh2ru66
// SIG // DfPkTdmg+2igrhQ7s4fBuwIDAQABo4HbMIHYMDQGCCsG
// SIG // AQUFBwEBBCgwJjAkBggrBgEFBQcwAYYYaHR0cDovL29j
// SIG // c3AudmVyaXNpZ24uY29tMBIGA1UdEwEB/wQIMAYBAf8C
// SIG // AQAwQQYDVR0fBDowODA2oDSgMoYwaHR0cDovL2NybC52
// SIG // ZXJpc2lnbi5jb20vVGhhd3RlVGltZXN0YW1waW5nQ0Eu
// SIG // Y3JsMBMGA1UdJQQMMAoGCCsGAQUFBwMIMA4GA1UdDwEB
// SIG // /wQEAwIBBjAkBgNVHREEHTAbpBkwFzEVMBMGA1UEAxMM
// SIG // VFNBMjA0OC0xLTUzMA0GCSqGSIb3DQEBBQUAA4GBAEpr
// SIG // +epYwkQcMYl5mSuWv4KsAdYcTM2wilhu3wgpo17IypMT
// SIG // 5wRSDe9HJy8AOLDkyZNOmtQiYhX3PzchT3AxgPGLOIez
// SIG // 6OiXAP7PVZZOJNKpJ056rrdhQfMqzufJ2V7duyuFPrWd
// SIG // tdnhV/++tMV+9c8MnvCX/ivTO1IbGzgn9z9KMIID/zCC
// SIG // AuegAwIBAgIQDekr8NTYKYgYMgUJXpp2iDANBgkqhkiG
// SIG // 9w0BAQUFADBTMQswCQYDVQQGEwJVUzEXMBUGA1UEChMO
// SIG // VmVyaVNpZ24sIEluYy4xKzApBgNVBAMTIlZlcmlTaWdu
// SIG // IFRpbWUgU3RhbXBpbmcgU2VydmljZXMgQ0EwHhcNMDMx
// SIG // MjA0MDAwMDAwWhcNMDgxMjAzMjM1OTU5WjBXMQswCQYD
// SIG // VQQGEwJVUzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4x
// SIG // LzAtBgNVBAMTJlZlcmlTaWduIFRpbWUgU3RhbXBpbmcg
// SIG // U2VydmljZXMgU2lnbmVyMIIBIjANBgkqhkiG9w0BAQEF
// SIG // AAOCAQ8AMIIBCgKCAQEAslAoSN3TaHqEGERmdV1+xLif
// SIG // Yyb/PUOcfBE4ECVVc9l1J2n9TrkgXNMK+aAbKu1VViFh
// SIG // 2B7b5Lwza8fv3aM3ZY4bkwy2Ux5cfGY1XwWKRf52Tt9T
// SIG // gKKBIJ2uiFyiCPflMPnuIjdMQgrO38YfxNZV6YE/tVKj
// SIG // LKoBevKiqo01/p/mXWoFnz1r47+WwP7MYPlA5wegROuB
// SIG // UW6lKvK2ihAo7Y/cBqCGUJp7SggNMB3KEJ5r9+lYrgSp
// SIG // QJmyKOiPFqw841NvS9M1nbVvZB2zliy7Ped56216+Rbm
// SIG // Jq2v75lTt0Aslbh5qv7UUqspdH5C7DkeomoW5lm7JGjY
// SIG // AIBDEIeAawIDAQABo4HKMIHHMDQGCCsGAQUFBwEBBCgw
// SIG // JjAkBggrBgEFBQcwAYYYaHR0cDovL29jc3AudmVyaXNp
// SIG // Z24uY29tMAwGA1UdEwEB/wQCMAAwMwYDVR0fBCwwKjAo
// SIG // oCagJIYiaHR0cDovL2NybC52ZXJpc2lnbi5jb20vdHNz
// SIG // LWNhLmNybDAWBgNVHSUBAf8EDDAKBggrBgEFBQcDCDAO
// SIG // BgNVHQ8BAf8EBAMCBsAwJAYDVR0RBB0wG6QZMBcxFTAT
// SIG // BgNVBAMTDFRTQTIwNDgtMS01NDANBgkqhkiG9w0BAQUF
// SIG // AAOCAQEAh3hw2k5SASBb4HnJgjDE/bkZlr2RAMO9zc3G
// SIG // 9A7Y//lNwDNiMBHF9XQb1JLeX5wgE7F8Rb5QzYPngBeD
// SIG // pyeTZxNG+8q4mEEDzJtRWwWLf6hv8xtQGyQu8mmNbCL3
// SIG // u8oWle0MdMBod9nrmWKHwXOQ+Il0eiOro5h7l7H3jylx
// SIG // TS51G0hB2vC1DSBU1negl4Jjaf0Jz4rwdbsJm9n5EVUm
// SIG // mmEyvnoCsHuGvqLDiyIseNE1drySc1z5ueZMFQojzOTS
// SIG // 1DQuSUAVPA9geiTGpWbvls9w6z7n9A1+3NF8o3ZxacGc
// SIG // T0cwNSGxoq8aYjwr2Y6qKgd72BizXHvinaVv/jyJrTCC
// SIG // BBIwggL6oAMCAQICDwDBAIs8PIgR0T72Y+zfQDANBgkq
// SIG // hkiG9w0BAQQFADBwMSswKQYDVQQLEyJDb3B5cmlnaHQg
// SIG // KGMpIDE5OTcgTWljcm9zb2Z0IENvcnAuMR4wHAYDVQQL
// SIG // ExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xITAfBgNVBAMT
// SIG // GE1pY3Jvc29mdCBSb290IEF1dGhvcml0eTAeFw05NzAx
// SIG // MTAwNzAwMDBaFw0yMDEyMzEwNzAwMDBaMHAxKzApBgNV
// SIG // BAsTIkNvcHlyaWdodCAoYykgMTk5NyBNaWNyb3NvZnQg
// SIG // Q29ycC4xHjAcBgNVBAsTFU1pY3Jvc29mdCBDb3Jwb3Jh
// SIG // dGlvbjEhMB8GA1UEAxMYTWljcm9zb2Z0IFJvb3QgQXV0
// SIG // aG9yaXR5MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB
// SIG // CgKCAQEAqQK9wXDmO/JOGyifl3heMOqiqY0lX/j+lUyj
// SIG // t/6doiA+fFGim6KPYDJr0UJkee6sdslU2vLrnIYcj5+E
// SIG // ZrPFa3piI9YdPN4PAZLolsS/LWaammgmmdA6LL8MtVgm
// SIG // wUbnCj44liypKDmo7EmDQuOED7uabFVhrIJ8oWAtd0zp
// SIG // mbRkO5pQHDEIJBSfqeeRKxjmPZhjFGBYBWWfHTdSh/en
// SIG // 75QCxhvTv1VFs4mAvzrsVJROrv2nem10Tq8YzJYJKCEA
// SIG // V5BgaTe7SxIHPFb/W/ukZgoIptKBVlfvtjteFoF3BNr2
// SIG // vq6Alf6wzX/WpxpyXDzKvPAIoyIwswaFybMgdxOF3wID
// SIG // AQABo4GoMIGlMIGiBgNVHQEEgZowgZeAEFvQcO9pcp4j
// SIG // UX4Usk2O/8uhcjBwMSswKQYDVQQLEyJDb3B5cmlnaHQg
// SIG // KGMpIDE5OTcgTWljcm9zb2Z0IENvcnAuMR4wHAYDVQQL
// SIG // ExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xITAfBgNVBAMT
// SIG // GE1pY3Jvc29mdCBSb290IEF1dGhvcml0eYIPAMEAizw8
// SIG // iBHRPvZj7N9AMA0GCSqGSIb3DQEBBAUAA4IBAQCV6AvA
// SIG // jfOXGDXtuAEk2HcR81xgMp+eC8s+BZGIj8k65iHy8FeT
// SIG // LLWgR8hi7/zXzDs7Wqk2VGn+JG0/ycyq3gV83TGNPZ8Q
// SIG // cGq7/hJPGGnA/NBD4xFaIE/qYnuvqhnIKzclLb5loRKK
// SIG // JQ9jo/dUHPkhydYV81KsbkMyB/2CF/jlZ2wNUfa98VLH
// SIG // vefEMPwgMQmIHZUpGk3VHQKl8YDgA7Rb9LHdyFfuZUnH
// SIG // UlS2tAMoEv+Q1vAIj364l8WrNyzkeuSod+N2oADQaj/B
// SIG // 0jaK4EESqDVqG2rbNeHUHATkqEUEyFozOG5NHA1itwqi
// SIG // jNPVVD9GzRxVpnDbEjqHk3Wfp9KgMIIEyTCCA7GgAwIB
// SIG // AgIQaguZT8AA3qoR1NhAmqi+5jANBgkqhkiG9w0BAQQF
// SIG // ADBwMSswKQYDVQQLEyJDb3B5cmlnaHQgKGMpIDE5OTcg
// SIG // TWljcm9zb2Z0IENvcnAuMR4wHAYDVQQLExVNaWNyb3Nv
// SIG // ZnQgQ29ycG9yYXRpb24xITAfBgNVBAMTGE1pY3Jvc29m
// SIG // dCBSb290IEF1dGhvcml0eTAeFw0wMDEyMTAwODAwMDBa
// SIG // Fw0wNTExMTIwODAwMDBaMIGmMQswCQYDVQQGEwJVUzET
// SIG // MBEGA1UECBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVk
// SIG // bW9uZDEeMBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0
// SIG // aW9uMSswKQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAg
// SIG // TWljcm9zb2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3Nv
// SIG // ZnQgQ29kZSBTaWduaW5nIFBDQTCCASAwDQYJKoZIhvcN
// SIG // AQEBBQADggENADCCAQgCggEBAKKEFVPYCzAONJX/OhvC
// SIG // 8y97bTcjTfPSjOX9r/3FAjQfJMflodxU7H4CdEer2zJY
// SIG // FhRRKTjxfrK0jDpHtTlOblTCMQw6bfvNzctQnBuup9jZ
// SIG // SiY/tcXLj5biSfJt2OmWPt4Fz/CmVTetL2DNgGFCoUlU
// SIG // Sg8Yt0vZk5kwWkd1ZLTTu922qwydT7hzOxg6qrSHjLCI
// SIG // sE1PH04RtTOA3w06ZG9ExzS9SpObvKYd+QUjTmApj8wq
// SIG // 8oSama2o2wpwe9Y0QZClt2bHXBsdozMOm1QDGj+YkLjM
// SIG // 5z0EdEMcj/c55rOsSHprKg5iAWE5dm79PpgHSxTxAUb9
// SIG // FQDgR9pP5AXkgCUCAQOjggEoMIIBJDATBgNVHSUEDDAK
// SIG // BggrBgEFBQcDAzCBogYDVR0BBIGaMIGXgBBb0HDvaXKe
// SIG // I1F+FLJNjv/LoXIwcDErMCkGA1UECxMiQ29weXJpZ2h0
// SIG // IChjKSAxOTk3IE1pY3Jvc29mdCBDb3JwLjEeMBwGA1UE
// SIG // CxMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSEwHwYDVQQD
// SIG // ExhNaWNyb3NvZnQgUm9vdCBBdXRob3JpdHmCDwDBAIs8
// SIG // PIgR0T72Y+zfQDAQBgkrBgEEAYI3FQEEAwIBADAdBgNV
// SIG // HQ4EFgQUKVy5G7bNM+67nll99+XKLsQNNCgwGQYJKwYB
// SIG // BAGCNxQCBAweCgBTAHUAYgBDAEEwCwYDVR0PBAQDAgFG
// SIG // MA8GA1UdEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQEEBQAD
// SIG // ggEBAEVY4ppBf/ydv0h3d66M2eYZxVe0Gr20uV8CoUVq
// SIG // OVn5uSecLU2e/KLkOIo4ZCJC37kvKs+31gbK6yq/4BqF
// SIG // fNtRCD30ItPUwG2IgRVEX2SDZMSplCyK25A3Sg+36NRh
// SIG // j3Z24dkl/ySElY0EVlSUoRw6PoK87qWHjByMS3lftUn6
// SIG // XjJpOh9UrXVN32TnMDzbZElE+/vEHEJx5qA9Re5rAJ+s
// SIG // Qr26EbNW5PvVoiqB2B9OolW+J49wpqJsG/9UioK8gUum
// SIG // obFmeqkXp8sGwEfrprPpMRVTPSoEv/9zSNyLJ0P8Y+ju
// SIG // JIdbvjbR6DH1Mtle33l6ujCsaYZK+4wRvxuNVFkwggTh
// SIG // MIIDyaADAgECAgphDn2nAAAAAABIMA0GCSqGSIb3DQEB
// SIG // BQUAMIGmMQswCQYDVQQGEwJVUzETMBEGA1UECBMKV2Fz
// SIG // aGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEeMBwGA1UE
// SIG // ChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSswKQYDVQQL
// SIG // EyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9zb2Z0IENv
// SIG // cnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29kZSBTaWdu
// SIG // aW5nIFBDQTAeFw0wMzEwMjUwNTU5MTRaFw0wNTAxMjUw
// SIG // NjA5MTRaMHQxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpX
// SIG // YXNoaW5ndG9uMRAwDgYDVQQHEwdSZWRtb25kMR4wHAYD
// SIG // VQQKExVNaWNyb3NvZnQgQ29ycG9yYXRpb24xHjAcBgNV
// SIG // BAMTFU1pY3Jvc29mdCBDb3Jwb3JhdGlvbjCCASIwDQYJ
// SIG // KoZIhvcNAQEBBQADggEPADCCAQoCggEBALIthwm1XN9V
// SIG // metSYufT9ONFcakyv5TyDukNrf6dxwRqWE6cpNHYREH7
// SIG // ZH4PZe7IF9pN29nWULQMVltsFohLvwPuUEiD7E+Ik5pR
// SIG // 45QZf/qzl6XOYG2f3UyTOL3NNFlx5obO6YOZoJa46uBE
// SIG // WxNCuTpITlRy9wiW5ADEggF2Q69hwtv65cXwAhPd+DW0
// SIG // Dw1SNkZ0Q7GiypzdfpnxNRF3+xUmAY7P4LgEeCoV/XLG
// SIG // YHaRDOdPshgYG2mJtPEvIRtm6sqRx0YNuRdYcVhWhmUj
// SIG // 0QIyrmSgb9pSlf373Y009cEMNaNH1+kbavoPRbToMh1w
// SIG // Gb3R+eVkH+uHN+pv1A2Dj/0CAwEAAaOCAUAwggE8MA4G
// SIG // A1UdDwEB/wQEAwIGwDAdBgNVHQ4EFgQUgo+nF2RkckTV
// SIG // MK4WhOJTkMs+ntQwEwYDVR0lBAwwCgYIKwYBBQUHAwMw
// SIG // gakGA1UdIwSBoTCBnoAUKVy5G7bNM+67nll99+XKLsQN
// SIG // NCihdKRyMHAxKzApBgNVBAsTIkNvcHlyaWdodCAoYykg
// SIG // MTk5NyBNaWNyb3NvZnQgQ29ycC4xHjAcBgNVBAsTFU1p
// SIG // Y3Jvc29mdCBDb3Jwb3JhdGlvbjEhMB8GA1UEAxMYTWlj
// SIG // cm9zb2Z0IFJvb3QgQXV0aG9yaXR5ghBqC5lPwADeqhHU
// SIG // 2ECaqL7mMEoGA1UdHwRDMEEwP6A9oDuGOWh0dHA6Ly9j
// SIG // cmwubWljcm9zb2Z0LmNvbS9wa2kvY3JsL3Byb2R1Y3Rz
// SIG // L0NvZGVTaWduUENBLmNybDANBgkqhkiG9w0BAQUFAAOC
// SIG // AQEASkjcMrB6Zz+LKwG0DPXbeUFd4Dftv1Fzf9b5fmxt
// SIG // f5+8paRIHqjj7ab1PIZqkVlM1FMU697uzcPckKMIsYtF
// SIG // Ov1qcPDOBAY2LjC0j0yr6t1DWc2DoNfupEmuECRPmf/V
// SIG // Avnshkyl2CLoARct6xOQmZoRgDMMGYh+BhnUMe1MyX67
// SIG // JMexveVUO+p3EImHbOCcgI3SEORKl6nqVxnXeCsNmzUn
// SIG // sTi+0r+tx/DmbVqfEPbK8fLgMKsBphPItjlkvQkJ7Yu2
// SIG // FBhqeinGdEs7R/EUycveF92yXNjMOYc8OUjFMHyOdVfu
// SIG // 7RugDYVyNWRJvqFJOOKanEp8gmQNAXhfVv6IdDGCBI0w
// SIG // ggSJAgEBMIG1MIGmMQswCQYDVQQGEwJVUzETMBEGA1UE
// SIG // CBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEe
// SIG // MBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSsw
// SIG // KQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9z
// SIG // b2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29k
// SIG // ZSBTaWduaW5nIFBDQQIKYQ59pwAAAAAASDAJBgUrDgMC
// SIG // GgUAoIGqMBkGCSqGSIb3DQEJAzEMBgorBgEEAYI3AgEE
// SIG // MBwGCisGAQQBgjcCAQsxDjAMBgorBgEEAYI3AgEVMCMG
// SIG // CSqGSIb3DQEJBDEWBBT0pHjlmgBIoTDGKv9pK+B78xXK
// SIG // ozBKBgorBgEEAYI3AgEMMTwwOqAYgBYAcwBlAHQAdAB4
// SIG // AHQAbAB5AC4AagBzoR6AHGh0dHA6Ly9vZmZpY2UubWlj
// SIG // cm9zb2Z0LmNvbSAwDQYJKoZIhvcNAQEBBQAEggEAEqjt
// SIG // +U+DeaJI/lzkLUXut4y+Zm3ZIFm+ga0c+eMymx5d4TH1
// SIG // mP1FuFYlpkruNui2ckZVSpXxmPFjcOdXOTNQFQXO4Z2B
// SIG // 5rFYOTEdiiVkVLu+CkHdHnyJOw5wUxD/uscAJu8VyUxc
// SIG // pojx/gCo4UQanDoCYzW2zVTQMW0SbSwTeYbxyiqUYPhh
// SIG // wAq4lfDcDKR8sf+PiHWYB0nqW7krzwaotwaiTfVjQggT
// SIG // do7Oq5UqG7qU4FFb4XmPofPdouDmKdfdEERe9kIajGma
// SIG // 1GBBTOJiuE0GdI2L12xuPmKKkusD903CPUqLOoSZRXJP
// SIG // nAM3qIY5/AO/WwJJF5Jyr41xdt8Ra6GCAf8wggH7Bgkq
// SIG // hkiG9w0BCQYxggHsMIIB6AIBATBnMFMxCzAJBgNVBAYT
// SIG // AlVTMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjErMCkG
// SIG // A1UEAxMiVmVyaVNpZ24gVGltZSBTdGFtcGluZyBTZXJ2
// SIG // aWNlcyBDQQIQDekr8NTYKYgYMgUJXpp2iDAMBggqhkiG
// SIG // 9w0CBQUAoFkwGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEH
// SIG // ATAcBgkqhkiG9w0BCQUxDxcNMDQwNDE2MDkzNzE1WjAf
// SIG // BgkqhkiG9w0BCQQxEgQQNA4usoEpbbPmmxHIgSGc/TAN
// SIG // BgkqhkiG9w0BAQEFAASCAQAYY8BLd8kfuetij9rnVt4Z
// SIG // XpSltpTk3VnP8Bnic07rjXGswQ6zKma1mmyhbsl8Xesd
// SIG // oAqzYgF/ZYnYwW+DYMqpWGJo/VH1SE498HCMKaMrtxA9
// SIG // v401PnyYXV0jsczkZMgVJsp+JH/YMZYLaG41/IBwu/lK
// SIG // 17nsReHfZppu9ut1bNU59V0455DJdA7JZC45y7zw9COp
// SIG // UMaTeiYKFXJIcELnnE3WIMQB534g+LKSlpFJmTU2TeZ5
// SIG // v5t1F8fryd5GQIM79JOb/I/qovi9YlhZZk0kkto+Z0AR
// SIG // 0K8Wbd/pB6Exi2gswlHChlAgaIYF/44mx4wGvqZVKy3o
// SIG // Zx2dniaNKCul
// SIG // End signature block
