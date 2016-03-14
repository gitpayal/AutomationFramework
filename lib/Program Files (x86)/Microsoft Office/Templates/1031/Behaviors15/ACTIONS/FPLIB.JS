// SharedLib v1.0

// FrontPage ECMAScript ADJECTIVE strings.
var FP_ADJECTIVE_URL	= String( "/*url*/" );
var FP_ADJECTIVE_HREF	= String( "/*href*/" );
var FP_ADJECTIVE_ID		= String( "/*id*/" );

// "window.returnValue" return codes, to be used on "window.close()"
var RETURN_OK			= 1;
var RETURN_CANCEL		= 0;

/////////////////////////////////////////////////////////////////////
//
// safeFocus
//
// Description:
//	Safely sets the focus to the given element. ELEMENT CURRENTLY 
//	IS REQUIRED TO HAVE AN ID!
//
// Arguments:
//	elm : object			Element to set focus to
//
// Return Value:  
//	none
//
/////////////////////////////////////////////////////////////////////

function safeFocus( elm )
{	
	setTimeout( "document.getElementById( '" + elm.id + "').focus()", 100 );
}

/////////////////////////////////////////////////////////////////////
//
// generateUniqueID
//
// Description:
//	Generates an HTML ID string unique to the current active document.
//
// Arguments:
//	oDoc, <strPrefix>
//
// Return Value:  
//	A unique ID string.
//
/////////////////////////////////////////////////////////////////////

function generateUniqueID()
{
	var strPrefix = "id";		// default ID

	var oDoc = arguments[0];		
	
	if( 1 < arguments.length )
	{
		strPrefix = arguments[1];			
	}

	var nPrefixLength	= strPrefix.length;
	var oElements		= oDoc.all;
	var nNewIndex		= 1;

	for( var nElement = 0; nElement < oElements.length; nElement++ )
	{
		var astrNameId = new Array();
		
		astrNameId[0] = oElements[nElement].id;
		if( astrNameId[0] )
		{
			var strXatId = 
				oElements[nElement].getAttribute("xat-id");
			if( strXatId )
			{
				var idx = strXatId.indexOf("{");
				if( idx >= 0 )
					strXatId = strXatId.substring(0,idx);
				astrNameId[0] = strXatId;
			}
		}
		astrNameId[1] = oElements[nElement].name;
		
		for( var i = 0; i < astrNameId.length; i++ )
		{	
			var strID = astrNameId[i];
			
			if( !strID )
				continue;
		
			var nLength = strID.length;

			if( ( 0 < nLength ) &&
				( strPrefix.length < nLength ) )
			{
				var strSub = strID.substr( 0, nPrefixLength );

				if( strSub == strPrefix )
				{
					var strNumber = strID.substr( nPrefixLength );

					if( strNumber >= nNewIndex )
					{
						nNewIndex = Number( strNumber ) + 1;				
					}
				}
			}
		}
	}
	
	return String( strPrefix + nNewIndex );
}

/////////////////////////////////////////////////////////////////////
//
// getDefaultIDPrefix
//
// Description:
//	Returns default ID prefix for given tag name
//
// Arguments:
//	none
//
// Return Value:  
//	The ID prefix
//
/////////////////////////////////////////////////////////////////////

function getDefaultIDPrefix( strTagName )
{
	if( stricmp( strTagName, "div" ) ) return "layer";
	else if( stricmp( strTagName, "img" ) ) return "img";
	
	
	// default prefix
	return "id";	
}

// SIG // Begin signature block
// SIG // MIIargYJKoZIhvcNAQcCoIIanzCCGpsCAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFJY5SFPGmGMy
// SIG // 9s1XzcfcudxKcVJooIIVkzCCA8QwggMtoAMCAQICEEe/
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
// SIG // 7RugDYVyNWRJvqFJOOKanEp8gmQNAXhfVv6IdDGCBIcw
// SIG // ggSDAgEBMIG1MIGmMQswCQYDVQQGEwJVUzETMBEGA1UE
// SIG // CBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEe
// SIG // MBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSsw
// SIG // KQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9z
// SIG // b2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29k
// SIG // ZSBTaWduaW5nIFBDQQIKYQ59pwAAAAAASDAJBgUrDgMC
// SIG // GgUAoIGkMBkGCSqGSIb3DQEJAzEMBgorBgEEAYI3AgEE
// SIG // MBwGCisGAQQBgjcCAQsxDjAMBgorBgEEAYI3AgEVMCMG
// SIG // CSqGSIb3DQEJBDEWBBTLH5MtxRX6vs6iLnLr2NTXqZRL
// SIG // yjBEBgorBgEEAYI3AgEMMTYwNKASgBAAZgBwAGwAaQBi
// SIG // AC4AagBzoR6AHGh0dHA6Ly9vZmZpY2UubWljcm9zb2Z0
// SIG // LmNvbSAwDQYJKoZIhvcNAQEBBQAEggEAT0TktCegYZKt
// SIG // RmpU6tWXaZOqVQtE67RjxVZRZGix2COGOvtzk0m8aeQi
// SIG // onjf94HsThDkvC9hNTiLqnZpZ5XWK4Al4AObuyFQX2zU
// SIG // iQ90aictL6/xpew1y2yC1o1Vf22ryAuvgQ4GRE/6Iz5s
// SIG // 7HDG88/1iJhtWQtLaIqhWKh7vOZxvXNi3iC2a3fwgNEP
// SIG // iibBIz0FsWkhwt/WCUdm3eF9PrdjkLIKNanjbQcfeam1
// SIG // oyimmofOgdumThmKQ9oyALUxub1qrFSZhotYy8awY9qs
// SIG // kpunduPshIkbNjbgbulNYzOm8W7A/JYhzHvAjat8kOr1
// SIG // weGmvO+wsaTPD3O+USQcgaGCAf8wggH7BgkqhkiG9w0B
// SIG // CQYxggHsMIIB6AIBATBnMFMxCzAJBgNVBAYTAlVTMRcw
// SIG // FQYDVQQKEw5WZXJpU2lnbiwgSW5jLjErMCkGA1UEAxMi
// SIG // VmVyaVNpZ24gVGltZSBTdGFtcGluZyBTZXJ2aWNlcyBD
// SIG // QQIQDekr8NTYKYgYMgUJXpp2iDAMBggqhkiG9w0CBQUA
// SIG // oFkwGAYJKoZIhvcNAQkDMQsGCSqGSIb3DQEHATAcBgkq
// SIG // hkiG9w0BCQUxDxcNMDQwNDE2MDkzNzExWjAfBgkqhkiG
// SIG // 9w0BCQQxEgQQohFlTL1FCcNaoKnks8opHjANBgkqhkiG
// SIG // 9w0BAQEFAASCAQBfoR6JZDNEPPNjsHHOO7FU6nKX7YFZ
// SIG // zabDHXeRiAB0vgLHmAyVzhtqQ8gOOAr4zoHWsjYRv/D0
// SIG // mXJK2JaTHVgl8sNYHUVD18F0kbz3dj8Ra14LCTaxF6Ue
// SIG // 9AdPxujBxrMZBg4yUnPCS5o7AcKcV2o3qM/gvHSlsfGt
// SIG // nIqp7zMjndr3Wd3rfyZKRvb9Q0zmaPa2c+Ic0rhtNENh
// SIG // jNxrs5oWkyRFVlKpApJNJqGnNlR/CruXV0fcnn1F/EzB
// SIG // fIEGG5NiqOO3QSvKSzYGjKfb4fhK7f2U3YB5hNDwXtVU
// SIG // eIe76TAYgwY2UuWQhcrrbP/kp1GZC1jMQjdv2/Jhyese
// SIG // cfcM
// SIG // End signature block
