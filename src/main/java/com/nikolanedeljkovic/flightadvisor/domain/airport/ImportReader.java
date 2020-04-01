package com.nikolanedeljkovic.flightadvisor.domain.airport;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

public class ImportReader<T> {

	public List<T> read(InputStream file, Class<? extends T> type) {
		CsvToBean<T> parser = new CsvToBeanBuilder<T>(
				new InputStreamReader(file, StandardCharsets.UTF_8)).withType(type)
						.withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).withEscapeChar(':').build();
		return parser.parse();
	}


}
