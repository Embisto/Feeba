package com.feeba.tools;

import static com.googlecode.charts4j.Color.BEIGE;
import static com.googlecode.charts4j.Color.BISQUE;
import static com.googlecode.charts4j.Color.BLACK;
import static com.googlecode.charts4j.Color.BLUE;
import static com.googlecode.charts4j.Color.CORAL;
import static com.googlecode.charts4j.Color.CRIMSON;
import static com.googlecode.charts4j.Color.CYAN;
import static com.googlecode.charts4j.Color.TURQUOISE;
import static com.googlecode.charts4j.Color.YELLOW;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.mcavallo.opencloud.formatters.HTMLFormatter;

import com.feeba.core.FeebaCore;
import com.feeba.data.Question;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.RadarChart;
import com.googlecode.charts4j.RadarPlot;
import com.googlecode.charts4j.RadialAxisLabels;
import com.googlecode.charts4j.Shape;
import com.googlecode.charts4j.Slice;

public class ChartFactory {

	public static JLabel barChart(int selectedIndex, String chartTitle) {

		Question ques = FeebaCore.currentSurvey.getQuestions().get(
				selectedIndex);
		double[] dataArray = new double[ques.getChoices().size()];
		for (int i = 0; i < ques.getChoices().size(); i++) {

			dataArray[i] = ((double) Collections.frequency(ques.getResults(),
					ques.getChoices().get(i)) / (double) ques.getResults()
					.size()) * 100;

		}
		Data data = new Data(dataArray);
		Plot plot = Plots.newPlot(data);
		plot.setColor(Color.newColor("17748F"));

		for (int j = 0; j < dataArray.length; j++) {
			plot.addTextMarker((int) dataArray[j] + " %", BLACK, 16, j);
		}
		BarChart chart = GCharts.newBarChart(plot);
		chart.setBarWidth(80);
		chart.setTitle(chartTitle, BLACK, 16);
		chart.setSize(600, 500);
		chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels(ques.getChoices()));
		chart.addYAxisLabels(AxisLabelsFactory.newAxisLabels(Arrays.asList(
				"0 %", "20 %", "40 %", "60 %", "80 %", "100 %")));

		JLabel label = new JLabel();
		try {
			label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart
					.toURLString()))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return label;
	}

	public static JLabel radarChart(int selectedIndex, String chartTitle) {

		Question ques = FeebaCore.currentSurvey.getQuestions().get(
				selectedIndex);
		double[] dataArray = new double[ques.getChoices().size()];
		for (int i = 0; i < ques.getChoices().size(); i++) {

			dataArray[i] = ((double) Collections.frequency(ques.getResults(),
					ques.getChoices().get(i)) / (double) ques.getResults()
					.size()) * 100;

		}
		Arrays.toString(dataArray);
		Data data = new Data(dataArray);
		RadarPlot plot = Plots.newRadarPlot(data);
		Color plotColor = Color.newColor("17748F");
		plot.addShapeMarkers(Shape.SQUARE, plotColor, 12);
		plot.addShapeMarkers(Shape.SQUARE, Color.WHITE, 8);
		plot.setColor(plotColor);
		plot.setLineStyle(LineStyle.newLineStyle(4, 1, 0));
		RadarChart chart = GCharts.newRadarChart(plot);
		chart.setTitle(chartTitle, BLACK, 20);
		chart.setSize(500, 500);
		chart.setSpline(true);
		RadialAxisLabels radialAxisLabels = AxisLabelsFactory
				.newRadialAxisLabels(ques.getChoices());
		radialAxisLabels.setRadialAxisStyle(BLACK, 12);
		chart.addRadialAxisLabels(radialAxisLabels);
		AxisLabels contrentricAxisLabels = AxisLabelsFactory
				.newAxisLabels(Arrays.asList("0 %", "20 %", "40 %", "60 %",
						"80 %", "100 %"));
		contrentricAxisLabels.setAxisStyle(AxisStyle.newAxisStyle(BLACK, 12,
				AxisTextAlignment.RIGHT));
		chart.addConcentricAxisLabels(contrentricAxisLabels);

		JLabel label = new JLabel();
		try {
			label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart
					.toURLString()))));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return label;

	}

	public static JLabel pieChart(int selectedIndex, String chartTitle) {

		Color[] colors = new Color[] { TURQUOISE, BEIGE, BISQUE, BLUE, YELLOW,
				CORAL, CRIMSON, CYAN };
		Question ques = FeebaCore.currentSurvey.getQuestions().get(
				selectedIndex);
		Slice[] slices = new Slice[ques.getChoices().size()];
		for (int i = 0; i < slices.length; i++) {
			slices[i] = Slice.newSlice(Collections.frequency(ques.getResults(),
					ques.getChoices().get(i)), colors[i], ques.getChoices()
					.get(i));

		}

		PieChart chart = GCharts.newPieChart();
		switch (slices.length) {
		case 1:
			chart = GCharts.newPieChart(slices[0]);
			break;
		case 2:
			chart = GCharts.newPieChart(slices[0], slices[1]);
			break;
		case 3:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2]);
			break;
		case 4:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2],
					slices[3]);
			break;
		case 5:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2],
					slices[3], slices[4]);
			break;
		case 6:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2],
					slices[3], slices[4], slices[5]);
			break;
		case 7:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2],
					slices[3], slices[4], slices[5], slices[6]);
			break;
		case 8:
			chart = GCharts.newPieChart(slices[0], slices[1], slices[2],
					slices[3], slices[4], slices[5], slices[6], slices[7]);
			break;

		}

		chart.setTitle(chartTitle, BLACK, 16);
		chart.setSize(700, 330);
		chart.setThreeD(true);
		JLabel label = new JLabel();
		try {
			label = new JLabel(new ImageIcon(ImageIO.read(new URL(chart
					.toURLString()))));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return label;
	}

	public static JLabel freetextChart(int selectedIndex, String name) {

		JLabel label = new JLabel();
		ArrayList<String> results = FeebaCore.currentSurvey.getQuestions()
				.get(selectedIndex).getResults();
		Cloud cloud = new Cloud();

		cloud.setMaxWeight(20);
		cloud.setDefaultLink("");

		for (int i = 0; i < results.size(); i++) {

			Tag tag = new Tag(results.get(i));
			cloud.addTag(tag);

		}

		HTMLFormatter formatter = new HTMLFormatter();
		formatter
		.setHtmlTemplateTag("<span style=\"font-size: %tag-weight%px; color: #17748F\">%tag-name%    </span>\n");
		String text = "<HTML><body>" + formatter.html(cloud) + "</body></html>";
		label.setText(text);
		return label;
	}

}
