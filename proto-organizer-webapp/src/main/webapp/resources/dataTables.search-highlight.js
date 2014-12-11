jQuery.fn.dataTableExt.oApi.fnSearchHighlighting = function(oSettings) {
    oSettings.oPreviousSearch.oSearchCaches = {};       
    oSettings.oApi._fnCallbackReg( oSettings, 'aoRowCallback', function( nRow, aData, iDisplayIndex, iDisplayIndexFull) {
    // Initialize search string array
    var searchStrings = [];
    var oApi = this.oApi;
    var cache = oSettings.oPreviousSearch.oSearchCaches;
    // Global search string
    // If there is a global search string, add it to the search string array
    if (oSettings.oPreviousSearch.sSearch) {
        searchStrings.push(oSettings.oPreviousSearch.sSearch);
    }
    // Individual column search option object
    // If there are individual column search strings, add them to the search string array
    if ((oSettings.aoPreSearchCols) && (oSettings.aoPreSearchCols.length > 0)) {
        for (var i in oSettings.aoPreSearchCols) {
            if (oSettings.aoPreSearchCols[i].sSearch) {
            searchStrings.push(oSettings.aoPreSearchCols[i].sSearch);
            }
        }
    }
    // Create the regex built from one or more search string and cache as necessary
    if (searchStrings.length > 0) {
        var sSregex = searchStrings.join("|");
        if (!cache[sSregex]) {
            var regRules = "("
            ,   regRulesSplit = sSregex.split(' ');

            regRules += "("+ sSregex +")";
            for(var i=0; i<regRulesSplit.length; i++) {
              regRules += "|("+ regRulesSplit[i] +")";
            }
            regRules += ")";

            // This regex will avoid in HTML matches
            cache[sSregex] = new RegExp(regRules+"(?!([^<]+)?>)", 'ig');
        }
        var regex = cache[sSregex];
    }
    // Loop through the rows/fields for matches
    jQuery('td', nRow).each( function(i) {
        // Take into account that ColVis may be in use
        var j = oApi._fnVisibleToColumnIndex( oSettings,i);
        // Only try to highlight if the cell is not empty or null
        if (aData[j]) {         
            // If there is a search string try to match
            if ((typeof sSregex !== 'undefined') && (sSregex)) {
                this.innerHTML = aData[j].replace( regex, function(matched) {
                    return "<span class='filterMatches'>"+matched+"</span>";
                });
            }
            // Otherwise reset to a clean string
            else {
                this.innerHTML = aData[j];
            }
        }
    });
    return nRow;
}, 'row-highlight');
return this;
};