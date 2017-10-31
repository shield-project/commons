# 中科大旗开发部通用工具包

### v1.0
2017-03-07<br/>
新增
* 提供springboot自动化配置

```+yml
com:
  daqsoft:
    captcha:
      random-code: 1234567890QWERTYUIOPASDFGHJKLZXCVBNM 随机验证码,给定生成字符串即可(与随机unicode范围区别，选择其中一个配置
      code-unicode-scope: 30000-50000   随机unicode范围验证码，给定unicode范围即可(与随机验证码区别，选择其中一个)
      world-space: 20   字体间隙
      font-size: 40     字体大小
      height: 55        图片高度
      width: 150        图片宽度
      back-ground-color:    背景色设置
        a: 0                透明度
        b: 120              RGB byte
        g: 120              
        r: 120              
      font-name:  宋体      字体
      code-count: 4         验证码个数
      font-style: bold      字体样式
      interfering-line-count: 150   干扰线
```
```+java
@Controller
public class CaptchaController {
    @Autowired
    CaptchaBufferImage captchaBufferImage;

    @RequestMapping(value = "/captcha")
    public ResponseEntity<byte[]> getCaptcha(String random) throws IOException, CaptchaException {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = captchaBufferImage.generateCaptcha().getBufferedImage();
        ImageIO.write(bufferedImage,"png",jpegOutputStream);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "captcha.png");
        headers.setContentLength(jpegOutputStream.size());
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(jpegOutputStream.toByteArray(),
                headers, HttpStatus.CREATED);
    }
}
```