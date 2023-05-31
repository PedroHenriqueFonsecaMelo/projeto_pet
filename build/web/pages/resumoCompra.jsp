<%-- 
    Document   : ResumoCompra
    Created on : 30 de mai. de 2023, 03:31:24
    Author     : Mateus
--%>


<%@page import="entidades.Pedidos"%>
<%@page import="Modelo.produtoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Resumo da Compra</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Roboto:wght@700&display=swap" rel="stylesheet">  

        <!-- Icon Font Stylesheet -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/resources/lib/flaticon/font/flaticon.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="<%= request.getContextPath()%>/resources/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="<%= request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="<%= request.getContextPath()%>/resources/css/style.css" rel="stylesheet">

        <!--templates carrinho-->
        <link href="<%= request.getContextPath()%>/resources/css/styleCarrinho.css" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

        <!--carrinho com produtos em javascript-->
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid border-bottom d-none d-lg-block">
            <div class="row gx-0">
                <div class="col-lg-4 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Endereço</h6>
                            <span>Rua Engenheiro Gualberto, 640, Centro, Mogi das Cruzes - SP, CEP: 08770-300</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 text-center border-start border-end py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Email</h6>
                            <span>amicaopetcenter@outlook.com</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 text-center py-2">
                    <div class="d-inline-flex align-items-center">
                        <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                        <div class="text-start">
                            <h6 class="text-uppercase mb-1">Telefone</h6>
                            <span>(11) 5081-8388/91086-2088</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Topbar End -->
        
    <header>
        <span> <strong>TOTAL DO PEDIDO</strong></span>
    </header>
        <%
                pedidosDAO pedido = new pedidosDAO();
                List<Pedidos> Cadastrado = pedido.listar();
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID do Pedido</th>
                    <th>Desconto</th>
                    <th>Observação</th>
                    <th>Preço</th>
                    <th>Quantidade</th>
                </tr>
            </thead>
            <tbody>
                <%
                for(Pedidos pedido:Cadastrado){
                %><tr>
                    <td><%= pedido.getIdpedidos() %> </td>
                    <td><%= pedido.getDesc()%></td>
                    <td><%= pedido.getPreco()%></td>
                    <td><%= pedido.getQuantidade()%></td>

                </tr><%
                
                }
                
                %>
            </tbody>
        </table>

        
        <!-- Footer Start -->
        <div class="container-fluid bg-light mt-5 py-5">
            <div class="container pt-5">
                <div class="row g-5">
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Contatos</h5>
                        <p class="mb-2"><i class="bi bi-geo-alt text-primary me-2"></i>Rua Engenheiro Gualberto, 640,
                            Centro,Mogi Das Cruzes - SP, CEP: 08770-300</p>
                        <p class="mb-2"><i class="bi bi-envelope-open text-primary me-2"></i>amicaopetcenter@outlook.com</p>
                        <p class="mb-0"><i class="bi bi-telephone text-primary me-2"></i>(11) 5081-8388 / (11) 91086-2088
                        </p>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Links Rápidos</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Home</a>
                            <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Quem
                                somos</a>
                            <a class="text-body mb-2" href="#"><i
                                    class="bi bi-arrow-right text-primary me-2"></i>Serviços</a>
                            <a class="text-body" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Fale
                                conosco</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Mais Populares</h5>
                        <div class="d-flex flex-column justify-content-start">
                            <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Home</a>
                            <a class="text-body mb-2" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Quem
                                somos</a>
                            <a class="text-body mb-2" href="#"><i
                                    class="bi bi-arrow-right text-primary me-2"></i>Serviços</a>
                            <a class="text-body" href="#"><i class="bi bi-arrow-right text-primary me-2"></i>Fale
                                conosco</a>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <h5 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Newsletter</h5>
                        <form action="">
                            <div class="input-group">
                                <input type="text" class="form-control p-3" placeholder="Email">
                                <button class="btn btn-primary">Assinar</button>
                            </div>
                        </form>
                        <h6 class="text-uppercase mt-4 mb-3">Follow Us</h6>
                        <div class="d-flex">
                            <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-twitter"></i></a>
                            <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-facebook"></i></a>
                            <a class="btn btn-outline-primary btn-square me-2" href="#"><i class="bi bi-linkedin"></i></a>
                            <a class="btn btn-outline-primary btn-square" href="#"><i class="bi bi-instagram"></i></a>
                        </div>
                    </div>
                    <div class="col-12 text-center text-body">
                        <a class="text-body" href="">Terms & Conditions</a>
                        <span class="mx-1">|</span>
                        <a class="text-body" href="">Privacy Policy</a>
                        <span class="mx-1">|</span>
                        <a class="text-body" href="">Customer Support</a>
                        <span class="mx-1">|</span>
                        <a class="text-body" href="">Payments</a>
                        <span class="mx-1">|</span>
                        <a class="text-body" href="">Help</a>
                        <span class="mx-1">|</span>
                        <a class="text-body" href="">FAQs</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="container-fluid bg-dark text-white-50 py-4">
            <div class="container">
                <div class="row g-5">

                </div>
            </div>
        </div>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary py-3 fs-4 back-to-top"><i class="bi bi-arrow-up"></i></a>


        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

        <script src="<%= request.getContextPath()%>../resources/lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Template Javascript -->
        <script src="<%= request.getContextPath()%>/resources/js/main.js"></script>
        <script src="<%= request.getContextPath()%>/resources/js/carrinho.js"></script>
    </body>
</html>
