(function(w) {
	/*
	 * Add a handler to jQuery's "ready" event
	 */
	$(w.document).ready(
	    function() {
	    	/*
	    	 * Create the tabs. We can give the tabs some
	    	 * options via an object parameter. Lets
	    	 * log to the console every time a tab is clicked.
	    	 */
	    	$( "#tabs" ).tabs(
	    		{
	    			activate: function(event, ui) {
	    				var oldTabText = ui.oldTab.find("a").text();
	    				var newTabText = ui.newTab.find("a").text();
	    				console.log(oldTabText + " => " + newTabText);
	    			}
	    		}	
	    	);
	    }	
    );
})(window);