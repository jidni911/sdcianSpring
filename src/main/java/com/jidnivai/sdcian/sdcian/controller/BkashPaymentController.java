package com.jidnivai.sdcian.sdcian.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.jidnivai.sdcian.sdcian.dto.bkash.CreateResponse;
import com.jidnivai.sdcian.sdcian.dto.bkash.CreateResponseRepository;
import com.jidnivai.sdcian.sdcian.dto.bkash.ExecuteResponse;
import com.jidnivai.sdcian.sdcian.dto.bkash.ExecuteResponseRepository;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyBuyer;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyBuyerRepository;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyItem;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyItemOrder;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyItemOrderRepository;
import com.jidnivai.sdcian.sdcian.dto.bkash.JerseyItemRepository;
import com.jidnivai.sdcian.sdcian.dto.bkash.OrderDTO;
import com.jidnivai.sdcian.sdcian.dto.bkash.TokenResponse;
import com.jidnivai.sdcian.sdcian.dto.bkash.TokenResponseRepository;

@Controller
@RequestMapping("bkashpayment")
public class BkashPaymentController {

    @Autowired
    private JerseyBuyerRepository jerseyBuyerRepository;

    @Autowired
    private JerseyItemRepository jerseyItemRepository;

    @Autowired
    private JerseyItemOrderRepository jerseyItemOrderRepository;

    @Autowired
    private ExecuteResponseRepository executeResponseRepository;

    @Autowired
    private TokenResponseRepository tokenResponseRepository;

    @Autowired
    private CreateResponseRepository createResponseRepository;

    @Value("${sdcian.app.bkash.base_URL}")
    private String base_URL;

    @Value("${sdcian.app.bkash.username}")
    private String username;

    @Value("${sdcian.app.bkash.password}")
    private String password;

    @Value("${sdcian.app.bkash.app_key}")
    private String app_key;

    @Value("${sdcian.app.bkash.app_secrete}")
    private String app_secrete;
    @Value("${sdcian.app.bkash.grant_path}")
    private String grant_path;

    @Value("${sdcian.app.bkash.create_path}")
    private String create_path;

    @Value("${sdcian.app.bkash.execute_path}")
    private String execute_path;

    @Value("${sdcian.app.bkash.callback_url}")
    private String callback_URL;

    private String id_token;

    private List<String> godFathers = Arrays.asList("01719987447");

    @GetMapping
    @ResponseBody
    public List<OrderDTO> index(@RequestHeader String number, @RequestHeader String password,
            @RequestParam String paid) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role != null && role.equals("admin")) {
            try {
                switch (paid) {
                case "true":
                    return jerseyItemOrderRepository.findAllPaidOrders();
                case "false":
                    return jerseyItemOrderRepository.findAllUnpaidOrders();
                case "":
                    return jerseyItemOrderRepository.findAllOrders();
                default:
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    @GetMapping("/allImagesList")
    @ResponseBody
    public List<String> getAllImagesList() {
        List<JerseyItem> jerseyItems = jerseyItemRepository.findAll();
        List<String> filenames = new ArrayList<>();
        for (JerseyItem jerseyItem : jerseyItems) {
            if (jerseyItem.getActive()) {
                jerseyItem.getImageUrlList().forEach(imageUrl -> {
                    filenames.add(imageUrl);
                });
            }
        }
        // File dir = new File(filePath);
        // File[] files = dir.listFiles();
        // List<String> filenames = new ArrayList<>();
        // for (File file : files) {
        // if (file.isFile()) {
        // filenames.add("/api/uploads/sdcianjersey/v25/" + file.getName());
        // }
        // }
        return filenames;
    }

    @PostMapping("signup")
    @ResponseBody
    public Boolean signup(@RequestParam String name, @RequestParam String number, @RequestParam String password) {
        if (number.trim().length() < 11 || password.trim().length() < 6 || name.trim().length() < 5) {
            return false;
        }
        if (jerseyBuyerRepository.existsByNumber(number)) {
            return false;
        }
        JerseyBuyer jerseyBuyer = new JerseyBuyer();
        jerseyBuyer.setName(name.trim());
        jerseyBuyer.setNumber(number.trim());
        jerseyBuyer.setPassword(password.trim());
        jerseyBuyer.setRole("user");
        jerseyBuyer.setActive(true);
        jerseyBuyerRepository.save(jerseyBuyer);
        return true;

    }

    @PostMapping("login")
    @ResponseBody
    public JerseyBuyer login(@RequestParam String number, @RequestParam String password) {
        if (number.trim().length() < 11 || password.trim().length() < 6) {
            return null;
        }
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(number);
        if (jerseyBuyer == null) {
            return null;
        }
        if (jerseyBuyer.getPassword().equals(password)) {
            return jerseyBuyer;
        } else {
            return null;
        }
    }

    @PostMapping("jerseyitem")
    @ResponseBody
    public JerseyItem savejerseyItem(@RequestBody JerseyItem jerseyItem, @RequestHeader String number,
            @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return null;
        }
        jerseyItem.setActive(true);
        return jerseyItemRepository.save(jerseyItem);
    }

    @GetMapping("jerseyitem")
    @ResponseBody
    public List<JerseyItem> getAlljerseyitem() {
        return jerseyItemRepository.findAll().stream().filter(jerseyItem -> jerseyItem.getActive())
                .collect(Collectors.toList());
    }

    @GetMapping("jerseyitem/{id}")
    @ResponseBody
    public JerseyItem getjerseyitembyid(@PathVariable Long id) {
        return jerseyItemRepository.findById(id).get();
    }

    @DeleteMapping("jerseyitem/{id}")
    @ResponseBody
    public JerseyItem deletejerseyitembyid(@PathVariable Long id, @RequestHeader String number,
            @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return null;
        }
        jerseyItemRepository.deactivateJerseyItemById(id);
        return jerseyItemRepository.findById(id).get();
    }

    @PostMapping("jerseyitemOrder")
    @ResponseBody
    public JerseyItemOrder saveJerseyItemOrder(@RequestBody JerseyItemOrder entity) {
        entity = jerseyItemOrderRepository.save(entity);

        return entity;
    }

    @GetMapping("jerseyitemOrder")
    @ResponseBody
    public List<JerseyItemOrder> getAllJerseyItemOrder(@RequestHeader String jerseyBuyerNumber,
            @RequestHeader String jerseyBuyerPassword) {
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(jerseyBuyerNumber);
        if (jerseyBuyer == null || !jerseyBuyer.getPassword().equals(jerseyBuyerPassword)) {
            return new ArrayList<>();
        }
        return jerseyItemOrderRepository.findAllByJerseyBuyer(jerseyBuyer);
    }

    @GetMapping("jerseyitemOrder/{id}")
    @ResponseBody
    public JerseyItemOrder getJerseyItemOrder(@PathVariable Long id, @RequestHeader String jerseyBuyerNumber,
            @RequestHeader String jerseyBuyerPassword) {
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(jerseyBuyerNumber);
        if (jerseyBuyer == null || !jerseyBuyer.getPassword().equals(jerseyBuyerPassword)) {
            return null;
        }

        return jerseyItemOrderRepository.findById(id).get();
    }

    @GetMapping("pay/{orderId}/{payto}/{paymentmethod}")
    @ResponseBody
    public HashMap<String, String> pay(@PathVariable Long orderId, @PathVariable String payto,
            @PathVariable String paymentmethod, @RequestHeader String jerseyBuyerNumber) {
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(jerseyBuyerNumber);
        if (jerseyBuyer == null) {
            return null;
        }
        String name = jerseyBuyer.getName();
        JerseyItemOrder jerseyItemOrder = jerseyItemOrderRepository.findById(orderId).get();
        jerseyItemOrder.setPaidTo(payto);
        jerseyItemOrder.setPaymentMethod(paymentmethod);
        Integer amount = jerseyItemOrder.getTotal();
        if (paymentmethod.equals("Bkash PGW") && payto.equals("Online")) {
            CreateResponse createResponse = generateBkashCall(name, amount);
            jerseyItemOrder.setPaymentId(createResponse.getPaymentID());
            HashMap<String, String> responseMap = new HashMap<>();
            responseMap.put("url", createResponse.getBkashURL());
            System.out.println(createResponse.getBkashURL());
            jerseyItemOrderRepository.save(jerseyItemOrder);
            return responseMap;

        }
        jerseyItemOrderRepository.save(jerseyItemOrder);
        return null;
    }

    @GetMapping("buyer")
    @ResponseBody
    public List<JerseyBuyer> getAllBuyer(@RequestHeader String number, @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return null;
        }
        if (godFathers.contains(number.trim())) {

            return jerseyBuyerRepository.findAll();
        }
        return null;
    }

    @PostMapping("buyer/{buyerNumber}/resetPassword")
    @ResponseBody
    public Boolean resetPassword(@PathVariable String buyerNumber, @RequestParam String newPassword,
            @RequestHeader String number, @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return false;
        }
        if (!godFathers.contains(number.trim())) {
            return false;
        }
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(buyerNumber);
        if (jerseyBuyer == null || newPassword.trim().length() < 6) {
            return false;
        }
        jerseyBuyer.setPassword(newPassword.trim());
        jerseyBuyerRepository.save(jerseyBuyer);
        return true;
    }

    @PostMapping("/buyer/{buyerNumber}/changeRole")
    @ResponseBody
    public Boolean changeRole(@PathVariable String buyerNumber, @RequestHeader String number,
            @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return false;
        }
        if (!godFathers.contains(number.trim())) {
            return false;
        }
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(buyerNumber);
        if (jerseyBuyer == null) {
            return false;
        }
        jerseyBuyer.setRole(jerseyBuyer.getRole().equals("user") ? "admin" : "user");
        jerseyBuyerRepository.save(jerseyBuyer);
        return true;
    }

    @PostMapping("/buyer/{buyerNumber}/changeActive")
    @ResponseBody
    public Boolean changeActive(@PathVariable String buyerNumber, @RequestHeader String number,
            @RequestHeader String password) {
        String role = jerseyBuyerRepository.findRoleByNumberAndPassword(number, password);
        if (role == null || !role.equals("admin")) {
            return false;
        }
        if (!godFathers.contains(number.trim())) {
            return false;
        }
        JerseyBuyer jerseyBuyer = jerseyBuyerRepository.findByNumber(buyerNumber);
        if (jerseyBuyer == null) {
            return false;
        }
        jerseyBuyer.setActive(!jerseyBuyer.isActive());
        jerseyBuyerRepository.save(jerseyBuyer);
        return true;
    }

    @GetMapping("testpayment")
    public String testPayment(@RequestParam String name, @RequestParam String number, @RequestParam String amount) {

        CreateResponse createResponse = generateBkashCall(name, Integer.parseInt(amount));
        return "redirect:" + createResponse.getBkashURL();
    }

    private CreateResponse generateBkashCall(String payerReference, Integer amount) {
        // System.out.println(base_URL);
        String grant_URL = base_URL + grant_path;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("username", username);
        headers.set("password", password);

        Map<String, Object> body = new HashMap<>();
        body.put("app_key", app_key);
        body.put("app_secret", app_secrete);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // Make the POST request
        ResponseEntity<TokenResponse> response = restTemplate.postForEntity(grant_URL, entity, TokenResponse.class);

        tokenResponseRepository.save(response.getBody());
        // Handle response
        // System.out.println("Status Code: " + response.getStatusCode());
        // System.out.println("Response Body: " + response.getBody());

        id_token = response.getBody().getId_token();

        // Create Payment

        String create_URL = base_URL + create_path;
        headers.clear();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", id_token);
        headers.set("X-App-Key", app_key);

        body.clear();
        body.put("mode", "0011");
        body.put("payerReference", payerReference);
        body.put("callbackURL", callback_URL);
        body.put("merchantAssociationInfo", "MI05MID54RF09123456One");
        body.put("amount", amount);
        body.put("currency", "BDT");
        body.put("intent", "sale");
        body.put("merchantInvoiceNumber", "Inv0124");

        entity = new HttpEntity<>(body, headers);
        ResponseEntity<CreateResponse> createResponse = restTemplate.postForEntity(create_URL, entity,
                CreateResponse.class);

        System.out.println("Status Code: " + createResponse.getStatusCode());
        System.out.println("Response Body: " + createResponse.getBody());

        createResponseRepository.save(createResponse.getBody());

        return createResponse.getBody();
    }

    @GetMapping("callback")
    public String callBack(@RequestParam String paymentID, @RequestParam String status,
            @RequestParam String signature) {
        if (status.equals("success")) {
            String execute_URL = base_URL + execute_path;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", id_token);
            headers.set("X-App-Key", app_key);
            Map<String, Object> body = new HashMap<>();
            body.put("paymentID", paymentID);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<ExecuteResponse> response = restTemplate.postForEntity(execute_URL, entity,
                    ExecuteResponse.class);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());
            executeResponseRepository.save(response.getBody());

            if (response.getBody().getStatusCode().equals("0000")) {
                String paymemntId = response.getBody().getPaymentID();
                String trxId = response.getBody().getTrxID();
                JerseyItemOrder jerseyItemOrder = jerseyItemOrderRepository.findByPaymentId(paymemntId);
                jerseyItemOrder.setTrxId(trxId);
                jerseyItemOrder.setPaid(true);
                jerseyItemOrderRepository.save(jerseyItemOrder);

            }

            // return response.getBody();
        }
        return "redirect:https://api.sdcian.com";
    }

}
