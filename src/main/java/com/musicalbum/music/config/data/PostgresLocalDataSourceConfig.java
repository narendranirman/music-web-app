package com.musicalbum.music.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("postgres-local")
public class PostgresLocalDataSourceConfig extends AbstractLocalDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return createDataSource("jdbc:postgresql://armydb-pg.postgres.database.azure.com:5432/sandos_migration",
                "org.postgresql.Driver", "serveradmin@armydb-pg", "menlotech@123");
    }

}
