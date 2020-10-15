

package com.cts.bootup.run.shell;

import com.github.fonimus.ssh.shell.listeners.SshShellListener;
import com.github.fonimus.ssh.shell.postprocess.PostProcessor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.List;

/**
 * Demo configuration
 */
@Slf4j
@Configuration
public class AppConfiguration {

    @Bean
    public PostProcessor<String> quotePostProcessor() {
        return new PostProcessor<String>() {

            @Override
            public String getName() {
                return "quote";
            }

            @Override
            public String process(String result, List<String> parameters) {
                return "'" + result + "'";
            }
        };
    }

    @Bean
    public SshShellListener sshShellListener() {
        return event -> log.info("[listener] event '{}' [id={}, ip={}]",
                event.getType(),
                event.getSession().getServerSession().getIoSession().getId(),
                event.getSession().getServerSession().getIoSession().getRemoteAddress());
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.first-datasource")
    public DataSourceProperties firstDsProps() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.first-datasource.configuration")
    public DataSource firstDataSource() {
        return firstDsProps().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSourceProperties secondDsProps() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.second-datasource.configuration")
    public DataSource secondDataSource() {
        return secondDsProps().initializeDataSourceBuilder().build();
    }
}
