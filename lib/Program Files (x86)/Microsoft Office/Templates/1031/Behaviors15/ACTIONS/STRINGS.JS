// SharedLib v1.0

/////////////////////////////////////////////////////////////////////
//
// escapeString
//
// Description:
//	Escapes necessary characters in string paramter.
//	E.g. "\" to "\\"
//
// Arguments:
//	strToEscape : string		String to escape
//
// Return Value:  
//	The escaped string.
//
/////////////////////////////////////////////////////////////////////

function escapeString( strToEscape )
{
	var astrUnEscaped	= new Array();
	var astrEscaped		= new Array();
	
	// these are regexp syntax:
	astrUnEscaped[0]	= "\\\\";
	astrUnEscaped[1]	= "'";
	astrUnEscaped[2]	= '"';
	
	// these are strings
	astrEscaped[0]		= "\\\\";
	astrEscaped[1]		= "\\'";
	astrEscaped[2]		= '\\"';
	
	return ReplaceSubStrings( strToEscape, astrUnEscaped, astrEscaped );
}

/////////////////////////////////////////////////////////////////////
//
// unescapeString
//
// Description:
//	Converts any escaped characters in string parameter to original, unescaped character.
//	E.g. "\\" to "\"
//
// Arguments:
//	strToUnEscape : string		String to unescape
//
// Return Value:  
//	The unescaped string.
//
/////////////////////////////////////////////////////////////////////

function unescapeString( strToUnEscape )
{
	var astrUnEscaped	= new Array();
	var astrEscaped		= new Array();
	
	// these are regexp syntax:
	astrEscaped[0]		= "\\\\\\\\";
	astrEscaped[1]		= "\\\\'";
	astrEscaped[2]		= '\\\\"';

	// these are strings
	astrUnEscaped[0]	= "\\";
	astrUnEscaped[1]	= "'";
	astrUnEscaped[2]	= '"';
		
	return ReplaceSubStrings( strToUnEscape, astrEscaped, astrUnEscaped );
}

function escapeRegExpExpString( strToEscape )
{
	var astrUnEscaped	= new Array();
	var astrEscaped		= new Array();

	// these are regexp syntax:
	astrUnEscaped[0]	= "\\\\";
	astrUnEscaped[1]	= "\\(";
	astrUnEscaped[2]	= '\\)';
	astrUnEscaped[3]	= '\\.';
	astrUnEscaped[4]	= '\\$';
	astrUnEscaped[5]	= '\\{';
	astrUnEscaped[6]	= '\\}';
	astrUnEscaped[7]	= '\\[';
	astrUnEscaped[8]	= '\\]';
	astrUnEscaped[9]	= '\\*';
	
	// these are strings
	astrEscaped[0]		= "\\\\";
	astrEscaped[1]		= "\\(";
	astrEscaped[2]		= "\\)";
	astrEscaped[3]		= '\\.';
	astrEscaped[4]		= '\\$';
	astrEscaped[5]		= '\\{';
	astrEscaped[6]		= '\\}';
	astrEscaped[7]		= '\\[';
	astrEscaped[8]		= '\\]';
	astrEscaped[9]		= '\\*';
	
	return ReplaceSubStrings( strToEscape, astrUnEscaped, astrEscaped );
}

function ReplaceSubStrings( strToReplace, astrFind, astrReplace )
{
	for( var nSubString = 0; nSubString < astrFind.length; nSubString++ )
	{
		var re = new RegExp( astrFind[nSubString], "g" );			

		strToReplace = strToReplace.replace( re, astrReplace[nSubString] );	
	}
	
	return strToReplace;
}

function stricmp( str1, str2 )
{
	str1 = String( str1 );
	str2 = String( str2 );

	if( str1.toLowerCase() == str2.toLowerCase() ) return true;	
	
	return false;
}

function isLowerCase( ch )
{
	var str = String( ch );
	
	if( str.toLowerCase() == ch ) return true;	
	
	return false;
}

function isUpperCase( ch )
{
	var str = String( ch );
	
	if( str.toUpperCase() == ch ) return true;	
	
	return false;
}

/////////////////////////////////////////////////////////////////////
//
// isSpace
//
// Description:
//	Returns true if the given character is a particuliar representation
//	of a space character.
//
// Arguments:
//	ch : char				The character to test
//
// Return Value:  
//	True if ch is a white-space character, false otherwise.
//
/////////////////////////////////////////////////////////////////////

function isSpace( ch )
{
	switch( ch )
	{
	case ' ':
	case '\r':
	case '\t':
	case '\n':
		return true;
	}

	return false;
}

/////////////////////////////////////////////////////////////////////
//
// trimStringLeft
//
// Description:
//	Returns the passed in string with preceeding whitespace removed.
//
// Arguments:
//	strToTrim : string 			The string to be trimmed.
//
// Return Value:  
//	The trimmed string.
//
/////////////////////////////////////////////////////////////////////

function trimStringLeft( strToTrim )
{
	var nPos;

	for( nPos = 0; nPos < strToTrim.length; nPos++ )
	{
		if( !isSpace( strToTrim.charAt( nPos ) ) ) break;		
	}
	
	return strToTrim.substr( nPos, strToTrim.length - nPos );
}

/////////////////////////////////////////////////////////////////////
//
// trimStringRight
//
// Description:
//	Returns the passed in string with proceeding whitespace removed.
//
// Arguments:
//	strToTrim : string 			The string to be trimmed.
//
// Return Value:  
//	The trimmed string.
//
/////////////////////////////////////////////////////////////////////

function trimStringRight( strToTrim )
{
	var nPos;
	
	for( nPos = strToTrim.length - 1; nPos >= 0 ; nPos-- )
	{
		if( !isSpace( strToTrim.charAt( nPos ) ) ) break;		
	}
	
	return strToTrim.substr( 0, nPos + 1 );
}

/////////////////////////////////////////////////////////////////////
//
// trimQuotes
//
// Description:
//	Trims the preceeding and proceeding quotes around a given string.
//	This should be used when processing strings in a script call returned
//	from extractArgumentsFromScriptCall().
//
//	E.g. "'a string'" to "a string"
//
// Arguments:
//	strToTrim : string 			The string to be trimmed.
//
// Return Value:  
//	The trimmed string.
//
/////////////////////////////////////////////////////////////////////

function trimQuotes( strToTrim )
{	
	if( 0 < strToTrim.length )
	{
		if( ( '\'' == strToTrim.charAt( 0 ) ) ||
			( '\"' == strToTrim.charAt( 0 ) ) )
		{
			strToTrim = strToTrim.substr( 1, strToTrim.length - 1 );
		}
		
		if( ( '\'' == strToTrim.charAt( strToTrim.length - 1 ) ) ||
			( '\"' == strToTrim.charAt( strToTrim.length - 1 ) ) )
		{
			strToTrim = strToTrim.substr( 0, strToTrim.length - 1 );
		}
	}
	
	return strToTrim;
}

/*
function extractArguments( strScriptCall )
{	
	var		aRemoved = new Array();
	
	aRemoved['('] = new Array();
	aRemoved[')'] = new Array();
	aRemoved[','] = new Array();
	
	for( var nChar = 0; nChar < strScriptCall.length; nChar++ )
	{
		var	chQuote = null;
	
		if( '\'' == strScriptCall[nChar] || '\"' == strScriptCall[nChar] )
		{
			chQuote = strScriptCall[nChar];
		}
		
		if( chQuote )
		{
			var bLastWasEscape = false;
		
			for( nChar++ ; nChar < strScriptCall.length; nChar++ )
			{
				if( !bLastWasEscape )
				{
					if( chQuote == strScriptCall[nChar] )
					{
						break;
					}
				}
			
				var bRemove = false;
				
				if( '\\' == strScriptCall[nChar] )
				{
					bLastWasEscape = true;
				}
				else
				{
					bLastWasEscape = false;
				}
			
				if( '(' == strScriptCall[nChar] ) bRemove = true;
				else if( ')' == strScriptCall[nChar] ) bRemove = true;
				else if( ',' == strScriptCall[nChar] ) bRemove = true;
								
				if( bRemove )
				{
					aRemoved[strScriptCall[nChar]][aRemoved.length] = nChar;
					
					strScriptCall[nChar] = '_';
				}
			}
		}
	}

	var	aArguments = strArgumentList.split( "()," );
}
*/
/////////////////////////////////////////////////////////////////////
//
// extractArgumentsFromScriptCall
//
// Description:
//	Returns an array of objects containing information about 
//	the arguments of the original script call.
//
// Arguments:
//	strScriptCall : string 		The string returned by the extensions 
//									"getScriptCall" API function.
//
// Return Value:  
//	Array of "Return" objects, containing properties "strArgument" and
//	and "bIsURL".
//
/////////////////////////////////////////////////////////////////////

function extractArgumentsFromScriptCall( strScriptCall )
{	
	function Return( strArgument, bIsURL )
	{
		this.strArgument	= strArgument;
		this.bIsURL			= bIsURL;
	}

	var	aReturn				= new Array();
	var	nArgumentStartPos	= strScriptCall.indexOf( "(" ) + 1;
	var	nArgumentEndPos		= strScriptCall.lastIndexOf( ")" ) - 1;

	if( ( 0 <= nArgumentStartPos ) &&
		( nArgumentStartPos <= nArgumentEndPos ) )
	{
		var	strArgumentList 	= strScriptCall.substr( nArgumentStartPos, nArgumentEndPos - nArgumentStartPos + 1 );		
		var oArgs				= new VBArray( FrontPage.SplitArgs( strArgumentList, "," ) );
		var	aArguments			= oArgs.toArray();

		for( var nArg = 0; nArg < aArguments.length; nArg++ )
		{
			var	strArgument	= aArguments[nArg];
			var	nLength 	= strArgument.length;
			var	bIsURL		= false;

			for( var nPos = 0; nPos < nLength; nPos++ )
			{
				if( ( nPos + 1 < nLength ) &&
        			( '/' == strArgument.charAt( nPos ) ) &&
					( '*' == strArgument.charAt( nPos+1 ) ) )
				{
					var	nCommentStart 	= nPos + 2;
					var	nCommentEnd		= 0;
		
					for( nPos += 2; nPos < nLength; nPos++ )
					{
						if( ( nPos + 1 < nLength ) &&
							( '*' == strArgument.charAt( nPos ) ) &&
							( '/' == strArgument.charAt( nPos+1 ) ) )
						{
							nCommentEnd = nPos - 1;
		
							nPos += 2;

							break;
						}
					}

					if( nCommentStart <= nCommentEnd )
					{
						var strComment = strArgument.substr( nCommentStart, nCommentEnd - nCommentStart + 1 );

						strComment.toLowerCase();

						if( "url" == strComment )
						{
							bIsURL = true;
						}

						strArgument = strArgument.substr( nCommentEnd + 3, nLength - nCommentEnd - 2 );											
					}

					break;
				}
			}
			
			strArgument = trimStringLeft( trimStringRight( strArgument ) );
			
			aReturn[nArg] = new Return( strArgument, bIsURL );
		}	
	}

	return aReturn;
}

// HTML encode

function htmlEncode(sText) {
sText = ('' + sText).replace(/&/g,'&amp;');
sText = sText.replace(/</g,'&lt;');
sText = sText.replace(/>/g,'&gt;');
return sText.replace(/"/g,'&quot;');
}

// HTML decode

function htmlDecode(sText) {
sText = ('' + sText).replace(/&amp;/g,'&');
sText = sText.replace(/&lt;/g,'<');
sText = sText.replace(/&gt;/g,'>');
return sText.replace(/&quot;/g,'"');
}

// Use this function for localized strings.
//
// Usage:
//	strBuildParam( "%1 and then %2", "1st", "2nd" );
// Would return:
//	"1st and then 2nd"
//

function strBuildParam(stPattern)
{
	var re;

	var i;
	for (i = 1; i < strBuildParam.arguments.length; i++)
		{
		re = new RegExp("\\%" + i);
		stPattern = stPattern.replace(re, strBuildParam.arguments[i]);
		}
	return stPattern;
}

// SIG // Begin signature block
// SIG // MIIasgYJKoZIhvcNAQcCoIIaozCCGp8CAQExCzAJBgUr
// SIG // DgMCGgUAMGcGCisGAQQBgjcCAQSgWTBXMDIGCisGAQQB
// SIG // gjcCAR4wJAIBAQQQEODJBs441BGiowAQS9NQkAIBAAIB
// SIG // AAIBAAIBAAIBADAhMAkGBSsOAwIaBQAEFBcNWRk+BW0A
// SIG // UVgInwKKNapJv4azoIIVkzCCA8QwggMtoAMCAQICEEe/
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
// SIG // 7RugDYVyNWRJvqFJOOKanEp8gmQNAXhfVv6IdDGCBIsw
// SIG // ggSHAgEBMIG1MIGmMQswCQYDVQQGEwJVUzETMBEGA1UE
// SIG // CBMKV2FzaGluZ3RvbjEQMA4GA1UEBxMHUmVkbW9uZDEe
// SIG // MBwGA1UEChMVTWljcm9zb2Z0IENvcnBvcmF0aW9uMSsw
// SIG // KQYDVQQLEyJDb3B5cmlnaHQgKGMpIDIwMDAgTWljcm9z
// SIG // b2Z0IENvcnAuMSMwIQYDVQQDExpNaWNyb3NvZnQgQ29k
// SIG // ZSBTaWduaW5nIFBDQQIKYQ59pwAAAAAASDAJBgUrDgMC
// SIG // GgUAoIGoMBkGCSqGSIb3DQEJAzEMBgorBgEEAYI3AgEE
// SIG // MBwGCisGAQQBgjcCAQsxDjAMBgorBgEEAYI3AgEVMCMG
// SIG // CSqGSIb3DQEJBDEWBBTTVaXTViSdvLV70VPu2Dk65Dhc
// SIG // TzBIBgorBgEEAYI3AgEMMTowOKAWgBQAcwB0AHIAaQBu
// SIG // AGcAcwAuAGoAc6EegBxodHRwOi8vb2ZmaWNlLm1pY3Jv
// SIG // c29mdC5jb20gMA0GCSqGSIb3DQEBAQUABIIBAIZ+o/ZY
// SIG // KnNWbKx9FQcP5+Rd5ey0WrcRwBdyhlSMKqFHZaDOb2Dk
// SIG // lczwfC0Jrny/d7DCAxKH0T/6c7xCm18Lm90dv0E9LC3m
// SIG // k7iT5DKee4Jt1xN8Zk64uBZRoIllVZfzHJSc6cLuajSa
// SIG // iNDDKI36LbtQ9b7lMqupdz01BH2cJ9ufYLHK2tUeRGBD
// SIG // tGvZaZK8fuiyR8MQ7icJ4L7I78dGBTW73Exsoq5LYZeJ
// SIG // YAQ6UYytID/+mNheyG6OMAd0u4pCkIqJlIN2Pemh0zxe
// SIG // GUQw0Mw6TaDaj7lxjCnIOqXAvuZXp2+ZNdKrGhQaEDb+
// SIG // MTtgTmangtCbFpRSILZ2B6gagV+hggH/MIIB+wYJKoZI
// SIG // hvcNAQkGMYIB7DCCAegCAQEwZzBTMQswCQYDVQQGEwJV
// SIG // UzEXMBUGA1UEChMOVmVyaVNpZ24sIEluYy4xKzApBgNV
// SIG // BAMTIlZlcmlTaWduIFRpbWUgU3RhbXBpbmcgU2Vydmlj
// SIG // ZXMgQ0ECEA3pK/DU2CmIGDIFCV6adogwDAYIKoZIhvcN
// SIG // AgUFAKBZMBgGCSqGSIb3DQEJAzELBgkqhkiG9w0BBwEw
// SIG // HAYJKoZIhvcNAQkFMQ8XDTA0MDQxNjA5MzcxOFowHwYJ
// SIG // KoZIhvcNAQkEMRIEEMwxW/r/Q6uHMEmozMaEUQIwDQYJ
// SIG // KoZIhvcNAQEBBQAEggEATj1kPXLorlnW3+uSbKkBwCez
// SIG // 83d3fEkzE50ogMbU85LLdhRNcxMuslYjCURtQl60X3mc
// SIG // w358RKS+y82iE4NvN7MT+GU1U58NFzRgfacjVU9icMa0
// SIG // N/ZKU3zBcjHd23wfVSafnwTD0cKQmNgNE3z0Q59k0aoH
// SIG // rkaRvUeXL9yiDT2pjqyvaLRv3F3Gj97TLCBIM4RCqLUI
// SIG // vRxFM1G2Mk3E8mLBa+Vps8XfL5Vkuv7tgAnD7HRHFWgt
// SIG // b4+DyPOm7KP+ndbslG7AKWMZxFA3o9pwIT+fNZuFPbOe
// SIG // kmjaI5+MFsfpbiYfsDixyf/C26cQIyAJJPrChiS82p3h
// SIG // Z/1kM03DpA==
// SIG // End signature block
