package com.hpst.tdd.integration.flow;


import java.io.File;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hpst.tdd.Car;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Bean
    @StepScope
    FlatFileItemReader<Car> flatFileItemReader(@Value("${file}") File file) {
        FlatFileItemReader<Car> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(file));
        reader.setLineMapper(lineMapper());
        reader.setLinesToSkip(1); 
        return reader;
    }

	private DefaultLineMapper<Car> lineMapper() {
		
		return new DefaultLineMapper<Car>() {
            {
                this.setLineTokenizer(new DelimitedLineTokenizer(",") {
                    {
                        this.setNames(new String[]{"id", "name", "type"});
                    }
                });
                this.setFieldSetMapper(new BeanWrapperFieldSetMapper<Car>() {
                    {
                        this.setTargetType(Car.class);
                    }
                });
            }
        };
	}

    @Bean
    JdbcBatchItemWriter<Car> jdbcBatchItemWriter(DataSource h2) {
        JdbcBatchItemWriter<Car> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setDataSource(h2);
        jdbcWriter.setSql("insert into Car( id, name, type) values (:id,:name,:type)");
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return jdbcWriter;
    }

    
    
    @Bean
    Job CarEtl(JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            FlatFileItemReader<Car> reader,
            JdbcBatchItemWriter<Car> writer
    ) {

        Step step = stepBuilderFactory.get("file-to-database")
                .<Car, Car>chunk(5)
                .reader(reader)
                .writer(writer)
                .build();

        return jobBuilderFactory.get("caretl")
                .start(step)
                .build();
    }

    //@Bean
    CommandLineRunner runner(JobLauncher launcher,
                             Job job,
                             @Value("${file}") File in,
                             JdbcTemplate jdbcTemplate) {
        return args -> {

            JobExecution execution = launcher.run(job,
                    new JobParametersBuilder()
                            .addString("file", in.getAbsolutePath())
                            .toJobParameters());

            System.out.println("execution status: " + execution.getExitStatus().toString());

            List<Car> CarList = jdbcTemplate.query("select * from Car", (resultSet, i) -> new Car(Integer.valueOf(resultSet.getString("id")),
                    resultSet.getString("name"),
                    resultSet.getString("type")));

            CarList.forEach(System.out::println);

        };

    }

}