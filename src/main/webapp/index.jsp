<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html class="light" lang="pt-br">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>Processamento | CashbackPro</title>
    
    <!-- Importação do Tailwind e Fontes via CDN (Mantendo o padrão do Stitch) -->
    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>
    
    <script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "primary": "#0040e0",
                        "secondary": "#565e74",
                        "surface": "#f8f9ff",
                        "on-background": "#0b1c30"
                    }
                }
            }
        }
    </script>

    <style>
        body { font-family: 'Manrope', sans-serif; background-color: #f8f9ff; }
        .glass-card { background: rgba(255, 255, 255, 0.8); backdrop-filter: blur(10px); border: 1px solid rgba(255, 255, 255, 0.2); }
    </style>
</head>

<body class="bg-background text-on-background">

    <!-- Header / TopAppBar -->
    <header class="sticky top-0 z-50 flex justify-between items-center px-6 h-16 w-full bg-white/80 backdrop-blur-md shadow-sm border-b">
        <div class="flex items-center gap-4">
            <span class="text-xl font-black text-blue-700 tracking-tight">CashbackPro</span>
        </div>
        <div class="flex items-center gap-6 text-sm font-semibold text-slate-500">
            <nav class="hidden md:flex gap-8">
                <a href="#" class="hover:text-blue-700">Dashboard</a>
                <a href="#" class="text-blue-700 border-b-2 border-blue-700">Relatório</a>
            </nav>
            <div class="h-8 w-8 rounded-full bg-blue-100 flex items-center justify-center text-blue-700">NS</div>
        </div>
    </header>

    <div class="flex">
        <!-- Main Content -->
        <main class="flex-1 p-10">
            <div class="max-w-7xl mx-auto">
                
                <!-- Cabeçalho de Resultados -->
                <div class="mb-10 flex justify-between items-end">
                    <div>
                        <span class="text-xs font-bold text-primary tracking-widest uppercase block mb-1">Processamento concluído</span>
                        <h1 class="text-4xl font-extrabold text-on-background">Extrato de Fidelidade</h1>
                        <p class="text-secondary mt-2">Dados consolidados a partir do arquivo CSV importado.</p>
                    </div>
                    
                    <div class="flex gap-4">
                        <form action="upload" method="POST" enctype="multipart/form-data" class="flex gap-2">
                            <button type="button" onclick="location.reload()" class="flex items-center gap-2 px-6 py-3 border rounded-xl font-bold text-secondary hover:bg-slate-50 transition-all">
                                <span class="material-symbols-outlined text-sm">restart_alt</span> Novo Processamento
                            </button>
                            <button type="submit" class="flex items-center gap-2 px-6 py-3 bg-primary text-white rounded-xl font-bold shadow-lg hover:bg-blue-800 transition-all">
                                <span class="material-symbols-outlined text-sm">download</span> Exportar CSV
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Tabela de Resultados -->
                <div class="bg-white rounded-xl shadow-sm border overflow-hidden">
                    <table class="w-full text-left border-collapse">
                        <thead class="bg-slate-50 border-b">
                            <tr>
                                <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase">ID Cliente</th>
                                <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase">Nome</th>
                                <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase">Total Comprado</th>
                                <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase">Tier</th>
                                <th class="px-6 py-4 text-xs font-bold text-slate-500 uppercase text-right">Cashback Acumulado</th>
                            </tr>
                        </thead>
                        <tbody class="divide-y">
                            <%-- O Servlet deve enviar uma lista chamada 'clientes' --%>
                            <c:forEach var="cliente" items="${clientes}">
                                <tr class="hover:bg-blue-50/30 transition-colors">
                                    <td class="px-6 py-4 font-mono text-xs text-blue-600 font-bold">#${cliente.idCliente}</td>
                                    <td class="px-6 py-4 font-bold text-slate-800">${cliente.nome}</td>
                                    <td class="px-6 py-4 text-slate-600">
                                        R$ <fmt:formatNumber value="${cliente.totalComprado}" minFractionDigits="2"/>
                                    </td>
                                    <td class="px-6 py-4">
                                        <c:choose>
                                            <c:when test="${cliente.tier == 'PLATINUM'}">
                                                <span class="px-3 py-1 rounded-full text-[10px] font-black bg-slate-100 text-slate-600 border border-slate-300 uppercase">Platinum</span>
                                            </c:when>
                                            <c:when test="${cliente.tier == 'GOLD'}">
                                                <span class="px-3 py-1 rounded-full text-[10px] font-black bg-amber-50 text-amber-700 border border-amber-200 uppercase">Gold</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="px-3 py-1 rounded-full text-[10px] font-black bg-slate-50 text-slate-400 border border-slate-200 uppercase">Normal</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-6 py-4 text-right">
                                        <span class="text-primary font-extrabold">
                                            R$ <fmt:formatNumber value="${cliente.cashbackTotal}" minFractionDigits="2"/>
                                        </span>
                                    </td>
                                </tr>
                            </c:forEach>
                            
                            <%-- Caso a lista esteja vazia (Estado inicial) --%>
                            <c:if test="${empty clientes}">
                                <tr>
                                    <td colspan="5" class="px-6 py-20 text-center text-slate-400">
                                        <span class="material-symbols-outlined text-5xl mb-4 block">upload_file</span>
                                        Nenhum dado processado. Por favor, importe o arquivo CSV.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- Resumo das Regras (Footer) -->
                <div class="mt-8 p-6 bg-blue-50/50 rounded-xl border border-blue-100 flex gap-6 items-start">
                    <span class="material-symbols-outlined text-blue-600">info</span>
                    <div class="text-sm text-slate-600">
                        <h4 class="font-bold text-slate-800 mb-1">Lógica de Negócio Aplicada</h4>
                        <p>Cashback de 5% (Eletrônicos), 3% (Vestuário) e 1% (Outros). Bônus de Tier: Gold (+R$50) e Platinum (+R$50 + 2%).</p>
                    </div>
                </div>
                
            </div>
        </main>
    </div>

</body>
</html>