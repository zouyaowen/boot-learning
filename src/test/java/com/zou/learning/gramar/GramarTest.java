package com.zou.learning.gramar;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.zou.learning.entity.UserDO;
import com.zou.learning.util.PdfUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zou
 * @date 2020-02-14 2:28 下午
 */
@Slf4j
public class GramarTest {
    @Test
    public void test() {
        System.out.println("hello");
    }

    @Test
    public void pdf() {
        BaseFont bf;
        Font font = null;
        try {//创建字体
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
            font = new Font(bf, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("/Users/zou/file/a.pdf"));
            document.open();
            document.add(new Paragraph("hello word,邹耀文", font));
            document.close();
        } catch (Exception e) {
            System.out.println("file create exception");
            e.printStackTrace();
        }
    }

    @Test
    public void pdfRender() {
        BaseFont bf;
        Font font = null;
        try {//创建字体
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
            font = new Font(bf, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("/Users/zou/file/a.pdf"));
            document.open();
            document.add(new Paragraph("hello word,邹耀文", font));
            document.close();
        } catch (Exception e) {
            System.out.println("file create exception");
            e.printStackTrace();
        }
    }

    @Test
    public void pdfRenderWrite() {
        // 模板路径
        //String templatePath = "D:/pdm/one.pdf";
        // 生成的新文件路径
        //String newPDFPath = "D:/pdm/practise.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            reader = new PdfReader("/Users/zou/file/jiahui-medical-aggrement.pdf");// 读取pdf模板
            out = new FileOutputStream("/Users/zou/file/jiahui-medical-aggrement-generate.pdf");// 输出流
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"邹耀文", "16621792812", "兄弟", "邹耀文", "兄弟", "2020", "02", "14", "06", "34", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                if (name != null && name.equals("姓名")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("电话号码")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("关系")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("本人信息")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("签名")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("Date")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("月")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("日")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("小时")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                } else if (name != null && name.equals("分钟")) {
                    String value = str[i++];
                    System.out.println(name + "-------" + value);
                    form.setField(name, value);
                }
                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                Font font = new Font(bf, 8, Font.NORMAL);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int numberOfPages = reader.getNumberOfPages();
            System.out.println(numberOfPages);
            PdfImportedPage importPage = null;
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), page);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Test
    public void testDate() {
        StringBuilder sb = new StringBuilder();
        sb.append("aa");
        String a = null;
        sb.append(String.valueOf(a));
        sb.append("bb");
        System.out.println(sb.toString());


    }

    @Test
    public void pdfFillWrite() {
        // 模板路径
        //String templatePath = "D:/pdm/one.pdf";
        // 生成的新文件路径
        //String newPDFPath = "D:/pdm/practise.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            reader = new PdfReader("/Users/zou/file/src/fill-src.pdf");// 读取pdf模板
            out = new FileOutputStream("/Users/zou/file/dst/fill-dst.pdf");// 输出流
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"√", "✓", "✔", "☑", "邹耀文", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                String value = str[i++];
                System.out.println(name + "-------" + value);
                form.setField(name, value);
//                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//                Font font = new Font(bf, 8, Font.NORMAL);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int numberOfPages = reader.getNumberOfPages();
            System.out.println(numberOfPages);
            PdfImportedPage importPage = null;
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), page);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Test
    public void pdfFeiWrite() {
        // 模板路径
        //String templatePath = "D:/pdm/one.pdf";
        // 生成的新文件路径
        //String newPDFPath = "D:/pdm/practise.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            reader = new PdfReader("/Users/zou/file/src/fei-src.pdf");// 读取pdf模板
            out = new FileOutputStream("/Users/zou/file/dst/fei-dst.pdf");// 输出流
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"邹", "问", "122333", "16621792812", "邹耀文", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa"};
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                String value = str[i++];
                System.out.println(name + "-------" + value);
                form.setField(name, value);
//                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//                Font font = new Font(bf, 8, Font.NORMAL);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int numberOfPages = reader.getNumberOfPages();
            System.out.println(numberOfPages);
            PdfImportedPage importPage = null;
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), page);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    @Test
    public void pdfCheckWrite() {
        // 模板路径
        //String templatePath = "D:/pdm/one.pdf";
        // 生成的新文件路径
        //String newPDFPath = "D:/pdm/practise.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            reader = new PdfReader("/Users/zou/file/src/check2-src.pdf");// 读取pdf模板
            out = new FileOutputStream("/Users/zou/file/dst/check2-dst.pdf");// 输出流
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            String[] str = {"0", "a", "j", "m", "邹耀文", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa", "aa"};
            int i = 0;
            Map<String, AcroFields.Item> fields = form.getFields();
            Collection<AcroFields.Item> values = fields.values();
            for (AcroFields.Item value : values) {
                System.out.println("item.value=" + value.toString());
            }
//            Set<String> fieldKey = form.getFields().keySet();
//            for (String key : fieldKey) {
//                String field = form.getField(key);
//                System.out.println("key=" + key);
//                System.out.println("field=" + field);
//                if (key.equals("a")) {
//                    continue;
//                }
//                form.setField(key, str[i++], true);
//            }
            form.setField("a", "0", true);
            form.setField("b", "是", true);
            form.setField("c", "a", true);
            form.setField("d", "", true);
            String text2 = form.getField("Text2");
            form.setField("Text2", "aaa");
            System.out.println(text2);
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int numberOfPages = reader.getNumberOfPages();
            System.out.println(numberOfPages);
            PdfImportedPage importPage = null;
            for (int page = 1; page <= reader.getNumberOfPages(); page++) {
                importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), page);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }


    @Test
    public void testFei() {
        String url = "axsaafa/sfdsfdsa/zsczz.pdf";
        String substring = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(substring);


    }

    @Test
    public void testTime() {
        String abc = "null_Financial_Consent1581881778470";
        String s = abc.replaceAll("null", "acv");
        System.out.println(s);
    }

    @Test
    public void testaa() {
        String fileName = "null_Financial_Consent1581881778470-null_null-sds";
        fileName = fileName.replaceAll("null", "oooooooo");
        System.out.println(fileName);
    }

    @Test
    public void testC() {
        String fileName = "1-ass-";
        int i = fileName.indexOf("-");
        String substring = fileName.substring(i + 1);
        String type = fileName.substring(0, i);
        System.out.println(substring);
        System.out.println(type);
        LocalDateTime now = LocalDateTime.now();
        String yyyyMMddHHmmss = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(yyyyMMddHHmmss);
    }

    @Test
    public void testStr() {
        String abc = "aaaaaaa-------bbbbbbasd";
        String substring = abc.substring(0, abc.length() - 1);
        System.out.println(substring);
    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        ConcurrentHashMap<String, HashMap<String, Integer>> resMap = new ConcurrentHashMap();
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 5; i++) {
            final Integer temp = i;
            threadPool.execute(() -> {
                count.getAndIncrement();
                HashMap<String, Integer> dataMap = getDataMap(temp);
                resMap.put("number" + temp, dataMap);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count.get());
        System.out.println(JSON.toJSONString(resMap, true));
    }

    @Test
    public void testDept() {
        String a = "1.0";
        int i = a.indexOf(".");
        System.out.println(a.substring(0, i));
    }

    @Test
    public void testGramar() {
        String a = null;
        String qqq = new String("qqq");
        qqq = a;
        if (qqq == null || qqq.length() == 1) {
            System.out.println("异常");
        }
        System.out.println("异常2");
    }

    @Test
    public void testDateSixMonth() {
        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
        LocalDate localDate = now.plusMonths(6);
        System.out.println(localDate);
    }

    @Test
    public void testSecond() {
        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
        LocalDate localDate = now.plusDays(3);
        Period between = Period.between(LocalDate.now(), localDate);
        int days = between.getDays();
        System.out.println(days);
    }


    private HashMap<String, Integer> getDataMap(Integer i) {
        HashMap<String, Integer> resMap = new HashMap<>();
        resMap.put(i + "", i);
        return resMap;
    }

    @Test
    public void testExpireTime() {
        Long expireTime = 3283200L;
        Long now = 1583003863L;
        System.out.println(expireTime + now);
    }

    @Test
    public void testHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "hello");
        System.out.println(JSON.toJSONString(map));
        String orDefault = map.getOrDefault("world", "world");
        System.out.println(orDefault);
        System.out.println("---------------------");
    }

    @Test
    public void testReduce() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        String reduced = map.reduce(1, (key, value) -> key + "=" + value,
                (s1, s2) -> s1 + ", " + s2);


        System.out.println(reduced);
    }

    @Test
    public void testSearch() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        System.out.println(map);

        String result1 = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo") && value.equals("bar")) {
                return "foobar";
            }
            return null;
        });
        System.out.println(result1);
        System.out.println(map);
        String result2 = map.searchValues(1, value -> {
            System.out.println(Thread.currentThread().getName());
            if (value.length() > 3) {
                return value;
            }
            return null;
        });

        System.out.println(result2);
    }

    /**
     * CountDownLatch（闭锁）可以看作一个只能做减法的计数器，可以让一个或多个线程等待执行。CountDownLatch 有两个重要的方法：
     * <p>
     * countDown()：使计数器减 1；
     * await()：当计数器不为 0 时，则调用该方法的线程阻塞，当计数器为 0 时，可以唤醒等待的一个或者全部线程。
     * <p>
     * CountDownLatch 使用场景：
     *   以生活中的情景为例，比如去医院体检，通常人们会提前去医院排队，但只有等到医生开始上班，才能正式开始体检，
     * 医生也要给所有人体检完才能下班，这种情况就要使用 CountDownLatch，流程为：患者排队 → 医生上班 → 体检完成 → 医生下班。
     */
    @Test
    public void countDownLatch() throws InterruptedException {
        // 医院闭锁
        CountDownLatch hospitalLatch = new CountDownLatch(1);
        // 患者闭锁
        CountDownLatch patientLatch = new CountDownLatch(5);
        System.out.println("患者排队");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    hospitalLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("体检：" + j);
                patientLatch.countDown();
            });
        }
        System.out.println("医生上班");
        hospitalLatch.countDown();
        patientLatch.await();
        System.out.println("医生下班");
        executorService.shutdown();
    }

    /**
     * CyclicBarrier（循环屏障）通过它可以实现让一组线程等待满足某个条件后同时执行。
     * CyclicBarrier 经典使用场景是公交发车，为了简化理解我们这里定义，每辆公交车只要上满 4 个人就发车，
     * 后面来的人都会排队依次遵循相应的标准。
     * 它的构造方法为 CyclicBarrier(int parties,Runnable barrierAction) 其中，parties表示有几个线程来参与等待，
     * barrierAction 表示满足条件之后触发的方法。CyclicBarrier 使用 await() 方法来标识当前线程已到达屏障点，然后被阻塞。
     * <p>
     * 备注：循环屏障的满足条件是线程组所有线程开始等待
     * 理解：所有线程必须等待的一个栅栏，直到所有线程都到达这里，然后所有线程才可以继续做其他事情
     * 描述：通过调用 CyclicBarrier 对象的 await() 方法，N个线程可以实现互相等待。
     * 一旦 N 个线程在等待 CyclicBarrier 达成，所有线程将被释放掉去继续运行
     */
    @Test
    public void CyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("发车了");
            }
        });
        for (int i = 0; i < 4; i++) {
            new Thread(new CyclicWorker(cyclicBarrier)).start();
        }
    }

    static class CyclicWorker implements Runnable {
        private CyclicBarrier cyclicBarrier;

        CyclicWorker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println("乘客：" + i);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void semaphore() {
        Semaphore semaphore = new Semaphore(2);
        ThreadPoolExecutor semaphoreThread = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 5; i++) {
            semaphoreThread.execute(() -> {
                try {
                    // 堵塞获取许可
                    semaphore.acquire();
                    System.out.println("Thread：" + Thread.currentThread().getName() + " 时间：" + LocalDateTime.now());
                    TimeUnit.SECONDS.sleep(2);
                    // 释放许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    @Test
    public void concurrentNavigableMap() {
        ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<String, String>();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("5", "Five");
        map.put("6", "Six");

        System.out.println("Initial ConcurrentHashMap: " + map);
        System.out.println("HeadMap(\"2\") of ConcurrentHashMap: " + map.headMap("2"));
        System.out.println("TailMap(\"2\") of ConcurrentHashMap: " + map.tailMap("2"));
        System.out.println("SubMap(\"2\", \"4\") of ConcurrentHashMap: " + map.subMap("2", "4"));
        //https://www.yiibai.com/java_concurrency/concurrency_concurrentnavigablemap.html

    }


    @Test
    public void Exchanger() throws IOException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    String data1 = "zxx";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {
                }
            }
        });
        service.execute(new Runnable() {
            public void run() {
                try {
                    Thread.sleep((long) (Math.random() * 10000));
                    String data1 = "lhm";
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "正在把数据" + data1 + "换出去");
                    String data2 = (String) exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "换回的数据为" + data2);
                } catch (Exception e) {
                }
            }
        });
        // 线程还没有开始启动，主线程已经关闭，只要我们加入一段代码，让主线程不关闭，这样就可以跑子线程的方法
        // 加入该代码，让主线程不挂掉
        System.in.read();
    }

    @Test
    public void forkJoinPool() throws IOException {
        SumTask sumTask = new SumTask(1, 1000);
        sumTask.fork();
        System.out.print("result:" + sumTask.join());
        System.in.read();
    }

    @Test
    public void forkJoinPool2() throws IOException {
        // 无论初始化多少线程，都只会有和CPU数量相等的几个线程运行
        ForkJoinPool forkJoinPool = new ForkJoinPool(100);
        SumTask sumTask = new SumTask(1, 10000);
        forkJoinPool.submit(sumTask);
        System.out.print("result:" + sumTask.join());
        System.in.read();
    }

    static class SumTask extends RecursiveTask<Integer> {

        private Integer start = 0;
        private Integer end = 0;

        /**
         * 通过构造方法进行变量传递
         *
         * @param start
         * @param end
         */
        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start < 100) {
                //小于100时直接返回结果
                int sumResult = 0;
                for (int i = start; i <= end; i++) {
                    sumResult += i;
                }
                return sumResult;
            } else {
                //大于一百时进行分割
                int middle = (end + start) / 2;
                SumTask leftSum = new SumTask(this.start, middle);
                SumTask rightSum = new SumTask(middle, this.end);
                leftSum.fork();
                rightSum.fork();
                return leftSum.join() + rightSum.join();
            }
        }
    }

    /**
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void completeFuture() throws ExecutionException, InterruptedException {

    }

    @Test
    public void testUnixTime() {
        System.out.println(1583116825 + 107624);
        System.out.println(1583116825 + 96235);
        System.out.println(1583116825 + 128500);
    }

    @Test
    public void testNull() {
        UserDO userDO = new UserDO();
        //System.out.println(userDO.getFf() == null);

    }

    @Test
    public void testDateMo() {
        Date date = new Date();
        System.out.println(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date time = cal.getTime();
        System.out.println(time);

    }

    @Test
    public void testDeptMo() {
        String dept = "|JIH|JIO|";
        String[] split = dept.split("\\|");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i] + "---");
        }

    }

    @Test
    public void testAp() {
        String start = "2020-03-10 21:05:00";
        String end = "2020-03-10 21:20:00";
        System.out.println(start.substring(0, 16) + "~" + end.substring(11, 16));
        System.out.println(end.substring(11, 16));
        System.out.println(end.substring(11, 16));
    }

    @Test
    public void testBig() {
        BigDecimal payMent = BigDecimal.ZERO;
        BigDecimal bigDecimal = new BigDecimal("12.22");
        BigDecimal add = payMent.add(bigDecimal);
        System.out.println(bigDecimal);
        System.out.println(payMent);
        System.out.println(add);
    }

    @Test
    public void testNow() {
        LocalDateTime now = LocalDateTime.now();
        String s = now.toString();
        System.out.println(s);
        String s1 = s.replaceAll("-", "/");
        String s2 = s1.replaceAll("T", "/");
        String s3 = s2.replaceAll(":", "/");
        String substring = s3.substring(0, 19);
        System.out.println(substring);
    }

    @Test
    public void testPow() {
        double pow1 = Math.pow(2, 2);
        System.out.println(pow1);
        double pow = Math.pow(2, 3);
        System.out.println(pow);
    }

    @Test
    public void testIfElse() {
        // 这里泛型 ? 是为方便演示，实际可替换为你需要的类型
        Map<String, Function<String, String>> map = new HashMap<>();
        // When init
        map.put("aaa", (x) -> {
            System.out.println(x);
            return x + "---";
        });
        String apply = map.get("aaa").apply("ddd");
        System.out.println(apply);
        // actionMappings.put(value2, (someParams) -> { doAction2(someParams)});
        // actionMappings.put(value3, (someParams) -> { doAction3(someParams)});
        // // 省略 null 判断
        // actionMappings.get(param).apply(someParams);
    }

    @Test
    public void testDateL() {
        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toString();
        System.out.println(yyyyMMddHHmmss);
    }

    @Test
    public void availableProcessors() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void length() {
        String str = "16621792812222";
        String substring = str.substring(0, 11);
        System.out.println(substring);
    }

    @Test
    public void pdf2Img() {
        String fileUrl = "http://10.2.3.178/static/2020/03/21/14/52/185a9c2133-6f5f-47eb-82b8-b54a25d935eaapp_apply_form.pdf";
        URL url;
        // 将pdf装图片 并且自定义图片得格式大小
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            url = new URL(fileUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            PDDocument document = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();
            ArrayList<String> resList = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // Windows native DPI
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                // BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
                ImageIO.write(image, "jpg", baos);
                baos.flush();
                //ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"_"+(i+1)+"."+type));
                String base64 = Base64Utils.encodeToString(baos.toByteArray());
                resList.add(base64);
                baos.close();
            }
        } catch (IOException e) {
            log.error("PDF Url转换图片Base64异常", e);
        }
    }

    @Test
    public void pdf2Img2() {
        String fileUrl = "http://10.2.3.178/static/2020/03/21/14/52/185a9c2133-6f5f-47eb-82b8-b54a25d935eaapp_apply_form.pdf";
        URL url;
        // 将pdf装图片 并且自定义图片得格式大小
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            url = new URL(fileUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            BufferedImage image = PdfUtil.pdfToImage(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            //ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"_"+(i+1)+"."+type));
            String base64 = Base64Utils.encodeToString(baos.toByteArray());
            baos.close();
            System.out.println(base64);
        } catch (IOException e) {
            log.error("PDF Url转换图片Base64异常", e);
        }
    }

    @Test
    public void pdf2Img3() throws IOException {
        String fileUrl = "http://10.2.3.178/static/2020/03/21/14/52/185a9c2133-6f5f-47eb-82b8-b54a25d935eaapp_apply_form.pdf";
        URL url;
        // 将pdf装图片 并且自定义图片得格式大小
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        url = new URL(fileUrl);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        inputStream = urlConnection.getInputStream();
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            int pageCounter = 0;
            for (PDPage page : document.getPages()) {
                // note that the page number parameter is zero based
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageCounter, 300, ImageType.RGB);
                // suffix in filename will be used as the file format
                ImageIOUtil.writeImage(bim, "aaa" + "-" + (pageCounter++) + ".png", 300);

            }
        } catch (IOException e) {
            log.error("render image error", e);
        }

    }

    @Test
    public void testPdf() throws IOException {
        log.info("-------------start-----------");
        File file = new File("aaa.pdf");
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
        int pageCount = doc.getNumberOfPages();
        ArrayList<String> resList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 方式1,第二个参数是设置缩放比(即像素)
            // BufferedImage image = renderer.renderImageWithDPI(i, 296);
            // 方式2,第二个参数是设置缩放比(即像素)
            BufferedImage image = renderer.renderImage(i, 1.25f);  //第二个参数越大生成图片分辨率越高，转换时间也就越长
            String imgName = "ccc.png";
            //ImageIO.write(image, "PNG", new File(imgName));
            ImageIO.write(image, "PNG", baos);
            String base64 = Base64Utils.encodeToString(baos.toByteArray());
            resList.add(base64);
        }
        doc.close();
        log.info("-------------start-----------");
    }

    @Test
    public void testAssert() {
        int a = 2;
        assert a == 1;
    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "ccc");
        map.put(4, "ddd");
        map.put(1, "aaa");
        map.put(2, "bbb");
        for (Integer integer : map.keySet()) {
            System.out.println(integer + map.get(integer));
        }
    }

    @Test
    public void testJSON() {
        List<UserDO> userDOS = JSON.parseArray(null, UserDO.class);
        System.out.println(userDOS);
        List<UserDO> userDOS2 = JSON.parseArray("", UserDO.class);
        System.out.println(userDOS2);
        if (!CollectionUtils.isEmpty(userDOS2)) {
            for (UserDO userDO : userDOS2) {
                System.out.println("--------");
            }
        }
    }

    @Test
    public void testName() {
        String nameCn = "zou yaowen";
        StringBuilder name = new StringBuilder();
        if (!StringUtils.isEmpty(name)) {
            String[] nameEnArr = nameCn.split(" ");
            if (nameEnArr.length > 1) {
                name.append(nameEnArr[1] + " " + nameEnArr[0]);
            } else {
                name.append(name);
            }
        }
        System.out.println(name.toString());
    }

    @Test
    public void testTime4() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String dayStartLimit = "77:00:00";
        String dayEndLimit = "07:01:00";
        LocalTime startTime = LocalTime.parse(dayStartLimit, dateTimeFormatter);
        LocalTime endTime = LocalTime.parse(dayEndLimit, dateTimeFormatter);
        if (!startTime.isBefore(endTime)) {
            System.out.println("err");
        } else {
            System.out.println("info");
        }


    }

    @Test
    public void testIJ() {
        String ss = "JIHI0300WWWWWWWWWWWWW";
        String substring = ss.substring(3, 4);
        System.out.println(substring);
    }

    @Test
    public void testStream() {
        ArrayList<UserDO> userList = new ArrayList<>();
        //userList.add(new UserDO(1,"aa","bb",22,33,LocalDateTime.now(),LocalDateTime.now()));
        userList.add(new UserDO(1, "bb", "bb", 22, 33, LocalDateTime.now(), LocalDateTime.now()));
        userList.add(new UserDO(1, "cc", "bb", 22, 33, LocalDateTime.now(), LocalDateTime.now()));
        userList.add(new UserDO(1, "aa", "bb", 22, 33, LocalDateTime.now(), LocalDateTime.now()));
        userList.add(new UserDO(1, "dd", "bb", 22, 33, LocalDateTime.now(), LocalDateTime.now()));
        Set<UserDO> set = new TreeSet<>(Comparator.comparing(UserDO::getUserName));
        set.addAll(userList);
        System.out.println(JSON.toJSONString(set, true));
        //------------
        int list = (int) userList.stream()
                .collect(Collectors.groupingBy(UserDO::getUserName, Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).count();
        System.out.println(list);
        //-------------
        int list1 = userList.stream()
                .collect(Collectors.groupingBy(a -> a.getUserName(), Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey())
                .collect(Collectors.toList()).size();
        System.out.println(list1);
    }

    @Test
    public void testDateAA() {
        LocalDateTime now = LocalDateTime.now();
        // 7天前
        LocalDateTime startTime = now.minusDays(7);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdtNow = now.atZone(zoneId);
        Date nowDate = Date.from(zdtNow.toInstant());
        ZonedDateTime zonedStartTime = startTime.atZone(zoneId);
        Date startDate = Date.from(zonedStartTime.toInstant());
        System.out.println(nowDate);
        System.out.println(startDate);
    }

}
