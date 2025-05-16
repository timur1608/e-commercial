package shopping.productservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("api/v1/products/images")
public class ImageController {
    @Value("${s3.config.bucket}")
    private String BUCKET;
    private final S3Presigner s3Presigner;

    @Autowired
    public ImageController(S3Presigner s3Presigner) {
        this.s3Presigner = s3Presigner;
    }

    @GetMapping
    public ResponseEntity<List<String>> getImages(@RequestParam("imagesUrl") List<String> imagesUrl) {
        List<GetObjectRequest> requests = imagesUrl.stream().map(url -> GetObjectRequest.builder().bucket(BUCKET).key(url).build())
                .toList();
        List<PresignedGetObjectRequest> presignedGetObjectRequestList = requests.stream()
                .map(request -> s3Presigner.presignGetObject(GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(5))
                        .getObjectRequest(request)
                        .build()))
                .toList();
        return ResponseEntity.ok(presignedGetObjectRequestList.stream().map(request -> request.url().toString()).toList());
    }
}
