//package com.ang.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.MDC;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.*;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description: skywalking对日志进行分析，处理微服务调用的服务不确定的问题。
// * @time: 2023/12/5 17:19
// */
//@Component
//@Slf4j
//public class MDCFilter extends OncePerRequestFilter {
//    private static final String STATUS = "status";
//    private static final String IP = "ip";
//    private static final String URI = "uri";
//    private static final String PARAM = "param";
//    private static final String RESP_BODY = "responseBody";
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        try {
//            //因为是threadlocalMap，需要clear()，不在finally中写的原因是 如果在doFilter抛出异常MDC还没写入log就被清空掉，无法打印出抛出异常的链路信息。
//            MDC.clear();
//            String uri = request.getRequestURI();
//            //如果是文件的话则不记录 有可能会过滤表单
//            if (ServletFileUpload.isMultipartContent(request)) {
//                chain.doFilter(request, response);
//                return;
//            }
//            String query = request.getQueryString() != null ? "?" + request.getQueryString() : "";
//            MDC.put(URI, uri);
//            MDC.put(IP, request.getRemoteAddr());
//            ResponseWrapper wrapperResponse = new ResponseWrapper(response);
//            String code = "";
//            if (request.getMethod().equals(HttpMethod.POST.name())) {
//                MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest(request);
//                //POST BODY参数
//                String requestBody = multiReadHttpServletRequest.getRequestBody();
//                MDC.put(PARAM, requestBody);
//                log.info("");
//                chain.doFilter(multiReadHttpServletRequest, wrapperResponse);
//            } else {
//                MDC.put(PARAM, query);
//                log.info("");
//                chain.doFilter(request, wrapperResponse);
//            }
//
//            byte[] bytes = wrapperResponse.getBytes();
//            String responseBody = new String(bytes);
//            //还需要重新写入response中
//            response.getOutputStream().write(bytes);
//
//            //确保返回不是 void
//            if (!StringUtils.isEmpty(responseBody)) {
//                code = getCodeOrStatus(responseBody);
//                MDC.put(STATUS, code);
//                MDC.put(RESP_BODY, responseBody);
//                log.info("");
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//
//    /**
//     * 获取status或者code的业务状态码
//     *
//     * @param responseBody
//     * @return
//     */
//    private String getCodeOrStatus(String responseBody) {
//        //由于多个项目可能有拥有不同的业务状态码
//        //json格式的数据以,或者}结尾。
//        return KmpUtils.search(responseBody, Arrays.asList("\"status\":", "\"code\":"), Arrays.asList(',', '}'));
//    }
//
//    /**
//     * HttpServletRequest 请求体多读
//     */
//    class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
//
//        // 缓存 RequestBody
//        private String requestBody;
//
//        MultiReadHttpServletRequest(HttpServletRequest request) {
//            super(request);
//            requestBody = "";
//            try {
//                StringBuilder stringBuilder = new StringBuilder();
//                InputStream inputStream = request.getInputStream();
//                byte[] bs = new byte[1024];
//                int len;
//                while ((len = inputStream.read(bs)) != -1) {
//                    stringBuilder.append(new String(bs, 0, len));
//                }
//                requestBody = stringBuilder.toString();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        @Override
//        public ServletInputStream getInputStream() throws IOException {
//            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody.getBytes());
//
//            return new ServletInputStream() {
//                @Override
//                public int read() throws IOException {
//                    return byteArrayInputStream.read();
//                }
//
//                @Override
//                public boolean isFinished() {
//                    return byteArrayInputStream.available() == 0;
//                }
//
//                @Override
//                public boolean isReady() {
//                    return true;
//                }
//
//                @Override
//                public void setReadListener(ReadListener readListener) {
//
//                }
//            };
//        }
//
//        @Override
//        public BufferedReader getReader() throws IOException {
//            return new BufferedReader(new InputStreamReader(this.getInputStream()));
//        }
//
//        String getRequestBody() {
//            return requestBody;
//        }
//    }
//
//    class ResponseWrapper extends HttpServletResponseWrapper {
//
//        private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        private HttpServletResponse response;
//        private PrintWriter printWriter;
//
//        public ResponseWrapper(HttpServletResponse response) {
//            super(response);
//            this.response = response;
//        }
//
//        @Override
//        public ServletOutputStream getOutputStream() throws IOException {
//            return new MyServletOutputStream(bytes); // 将数据写到 byte 中
//        }
//
//        /**
//         * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
//         */
//        @Override
//        public PrintWriter getWriter() throws IOException {
//            try{
//                printWriter = new PrintWriter(new OutputStreamWriter(bytes, "utf-8"));
//            } catch(UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            return printWriter;
//        }
//
//        /**
//         * 获取缓存在 PrintWriter 中的响应数据
//         * @return
//         */
//        public byte[] getBytes() {
//            if(null != printWriter) {
//                printWriter.close();
//                return bytes.toByteArray();
//            }
//
//            if(null != bytes) {
//                try {
//                    bytes.flush();
//                } catch(IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return bytes.toByteArray();
//        }
//
//        class MyServletOutputStream extends ServletOutputStream {
//            private ByteArrayOutputStream outputStream;
//
//            public MyServletOutputStream(ByteArrayOutputStream outputStream) {
//                this.outputStream = outputStream;
//            }
//
//            @Override
//            public void write(int b) throws IOException {
//                outputStream.write(b); // 将数据写到 stream　中
//            }
//
//            @Override
//            public boolean isReady() {
//                return true;
//            }
//
//            @Override
//            public void setWriteListener(WriteListener writeListener) {
//            }
//        }
//    }
//
//    public static class KmpUtils {
//        /**
//         * 获取kpm数组
//         *
//         * @param b 原字符串
//         * @return
//         */
//        private static int[] getNext(String b) {
//            int len = b.length();
//            int j = 0;
//            //next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
//            int[] next = new int[len + 1];
//            next[0] = next[1] = 0;
//            for (int i = 1; i < len; i++) {
//                //j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
//                while (j > 0 && b.charAt(i) != b.charAt(j)) {
//                    j = next[j];
//                }
//                if (b.charAt(i) == b.charAt(j)) {
//                    j++;
//                }
//                next[i + 1] = j;
//            }
//            return next;
//        }
//
//        /**
//         * kmp查找子字符串
//         *
//         * @param original 原字符串
//         * @param find     子字符串
//         * @param next     next数组
//         * @return
//         */
//        public static int search(String original, String find, int[] next) {
//            int j = 0;
//            for (int i = 0; i < original.length(); i++) {
//                char charAt = original.charAt(i);
//                while (j > 0 && charAt != find.charAt(j)) {
//                    j = next[j];
//                }
//                if (charAt == find.charAt(j)) {
//                    j++;
//                }
//                if (j == find.length()) {
//                    return i + 1;
//                }
//            }
//            return -1;
//        }
//
//        /**
//         * @param responseBody
//         * @param findStr       匹配其中之一
//         * @param eofCharacters 截止的字符
//         * @return
//         */
//        public static String search(String responseBody, List<String> findStr, List<Character> eofCharacters) {
//            //首先先去除第二层括号内的
//            //如果后期改造成统一的返回值字段 就不需要做该操作
//            int length = 0;
//            char[] chars = responseBody.toCharArray();
//            StringBuilder sb = new StringBuilder();
//            StringBuilder stringBuilder = new StringBuilder();
//            for (char aChar : chars) {
//                if (aChar == '{') {
//                    length++;
//                } else if (aChar == '}') {
//                    length--;
//                } else {
//                    //例如有以code为业务状态码的项目和status的项目使用的该功能
//                    //防止出现 {"code":200,data:{"user":"张三","status":"冻结"}} 优先匹配到status
//                    //导致输出状态码 "冻结"
//                    if (length == 1) {
//                        sb.append(aChar);
//                    }
//                }
//            }
//
//            String original = sb.toString();
//            int[] next = getNext(original);
//            for (String s : findStr) {
//                int index = search(original, s, next);
//                if (index != -1) {
//                    int end = original.length();
//                    for (Character eofCharacter : eofCharacters) {
//                        //...,"code":200} 以}结束
//                        //...,“code”:200,"data":...} 这种情况以,结束
//                        //因此这边取比较小的下标
//                        int i = original.indexOf(eofCharacter, index);
//                        if (i > index) {
//                            end = Math.min(i, end);
//                        }
//                    }
//                    stringBuilder.append(original.subSequence(index, end));
//                    return stringBuilder.toString();
//                }
//            }
//            return stringBuilder.toString().replaceAll("\"","");
//        }
//    }
//}
