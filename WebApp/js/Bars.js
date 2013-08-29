var Bars = (function() {

    var svg,
    dataSet = [ 5, 10, 13, 19, 21, 25, 22, 18, 15, 13, 11, 12, 15, 20, 18, 17, 16, 18, 23, 25 ],
    barPadding = 20,
    h,
    w;

	return {

		init: function() {

            //Width and height
            w = $(window).width();
            h = 200;

            //Create SVG element
            svg = d3.select("body")
                .append("svg")
                .attr("width", w)
                .attr("id","bars")
                .attr("height", h);

            svg.selectAll("rect")
                .data(dataSet)
                .enter()
                .append("rect")
                .attr("x", function(d, i) {
                    return i * (w / dataSet.length)+(barPadding/2); // (width/elements) + half barPadding to center bars
                })
                .attr("width", w / dataSet.length - barPadding)
                .attr("fill", "white")
                .attr("height", 0)
                .attr("y",h)
                .transition()
                .duration(1000)
                .attr("height", function(d) {
                    return d * 6;
                })
                .attr("y", function(d) {
                    return h - (d * 6);
                });
        },

        redraw: function(){
            dataSet = [];
            for (var i = 0; i < 25; i++) {           //Loop 25 times
                var newNumber = Math.floor(Math.random() * (30 - 5 + 1)) + 5;  //New random number (5-30)
                dataSet.push(newNumber);             //Add new number to array
            }
            svg.selectAll("rect")
                .data(dataSet)
                .transition()
                .duration(2000)
                .attr("height", function (d) {
                    return d * 6;
                })
                .attr("y", function (d) {
                    return h - (d * 6);
                });
        },

        reset: function() {

            dataSet = [ 5, 10, 13, 19, 21, 25, 22, 18, 15, 13,
                11, 12, 15, 20, 18, 17, 16, 18, 23, 25 ];

            svg.selectAll("rect")
                .data(dataSet)
                .transition()
                .duration(2000)
                .attr("height", function(d) { return d*6; })
                .attr("y", function(d) {
                    return h - (d * 6);
                });


        }


    }
})();