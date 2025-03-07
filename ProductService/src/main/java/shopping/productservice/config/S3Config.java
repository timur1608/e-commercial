package shopping.productservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URI;
import java.time.Duration;

@Slf4j
@Configuration
public class S3Config {
    @Value("${s3.config.bucket}")
    private String BUCKET;

    @Value("${s3.config.key_id}")
    private String KEY_ID;

    @Value("${s3.config.secret_key}")
    private String SECRET_KEY;

    @Value("${s3.config.s3_endpoint}")
    private String S3_ENDPOINT;

    @Value("${s3.config.region}")
    private String REGION;


    @Bean
    S3Presigner s3Presigner(AwsCredentials awsCredentials) {
        return S3Presigner.builder()
                .endpointOverride(URI.create(S3_ENDPOINT))
                .region(Region.of(REGION))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
    @Bean
    AwsCredentials awsCredentials() {
        return AwsBasicCredentials.create(KEY_ID, SECRET_KEY);
    }
}
