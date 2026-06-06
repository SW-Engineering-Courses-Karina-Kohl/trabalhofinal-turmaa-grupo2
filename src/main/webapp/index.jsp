<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html class="light" lang="pt-br">
<head>
    <meta charset="utf-8"/>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <title>CashbackPro | Processing Center</title>

    <script src="https://cdn.tailwindcss.com?plugins=forms,container-queries"></script>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:wght,FILL@100..700,0..1&display=swap" rel="stylesheet"/>

    <script id="tailwind-config">
        tailwind.config = {
            darkMode: "class",
            theme: {
                extend: {
                    colors: {
                        "on-secondary-fixed": "#131b2e",
                        "secondary-fixed": "#dae2fd",
                        "tertiary": "#784b00",
                        "outline-variant": "#c4c5d9",
                        "background": "#f8f9ff",
                        "on-tertiary-container": "#ffeedd",
                        "on-primary": "#ffffff",
                        "primary": "#0040e0",
                        "surface-variant": "#d3e4fe",
                        "surface-container-high": "#dce9ff",
                        "on-error": "#ffffff",
                        "primary-fixed": "#dde1ff",
                        "outline": "#747688",
                        "surface-container-lowest": "#ffffff",
                        "inverse-surface": "#213145",
                        "error-container": "#ffdad6",
                        "on-primary-fixed": "#001356",
                        "inverse-primary": "#b8c3ff",
                        "secondary-fixed-dim": "#bec6e0",
                        "on-secondary-container": "#5c647a",
                        "on-tertiary": "#ffffff",
                        "on-secondary-fixed-variant": "#3f465c",
                        "surface-container-highest": "#d3e4fe",
                        "surface-container": "#e5eeff",
                        "tertiary-fixed": "#ffddb8",
                        "surface-tint": "#124af0",
                        "primary-container": "#2e5bff",
                        "secondary": "#565e74",
                        "on-surface-variant": "#434656",
                        "primary-fixed-dim": "#b8c3ff",
                        "on-tertiary-fixed": "#2a1700",
                        "on-secondary": "#ffffff",
                        "error": "#ba1a1a",
                        "surface": "#f8f9ff",
                        "secondary-container": "#dae2fd",
                        "on-background": "#0b1c30",
                        "on-tertiary-fixed-variant": "#653e00",
                        "surface-dim": "#cbdbf5",
                        "on-surface": "#0b1c30",
                        "tertiary-container": "#996100",
                        "on-primary-fixed-variant": "#0035be",
                        "inverse-on-surface": "#eaf1ff",
                        "surface-container-low": "#eff4ff",
                        "surface-bright": "#f8f9ff",
                        "tertiary-fixed-dim": "#ffb95f",
                        "on-primary-container": "#efefff",
                        "on-error-container": "#93000a"
                    },
                    borderRadius: {
                        "DEFAULT": "0.25rem",
                        "lg": "0.5rem",
                        "xl": "0.75rem",
                        "full": "9999px"
                    },
                    spacing: {
                        "unit": "4px",
                        "card-padding": "24px",
                        "stack-lg": "32px",
                        "container-max": "1440px",
                        "stack-sm": "8px",
                        "gutter": "24px",
                        "margin-page": "40px",
                        "stack-md": "16px"
                    },
                    fontFamily: {
                        "h2": ["Manrope"],
                        "body-md": ["Manrope"],
                        "body-lg": ["Manrope"],
                        "label-caps": ["Manrope"],
                        "h3": ["Manrope"],
                        "h1": ["Manrope"],
                        "stat-value": ["Manrope"]
                    },
                    fontSize: {
                        "h2": ["32px", {"lineHeight": "1.2", "letterSpacing": "-0.01em", "fontWeight": "700"}],
                        "body-md": ["16px", {"lineHeight": "1.5", "fontWeight": "400"}],
                        "body-lg": ["18px", {"lineHeight": "1.6", "fontWeight": "400"}],
                        "label-caps": ["12px", {"lineHeight": "1", "letterSpacing": "0.05em", "fontWeight": "700"}],
                        "h3": ["24px", {"lineHeight": "1.3", "fontWeight": "700"}],
                        "h1": ["48px", {"lineHeight": "1.1", "letterSpacing": "-0.02em", "fontWeight": "800"}],
                        "stat-value": ["28px", {"lineHeight": "1", "fontWeight": "800"}]
                    }
                }
            }
        }
    </script>

    <style>
        .glass-effect {
            background: rgba(255, 255, 255, 0.7);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
        }
        .material-symbols-outlined {
            font-variation-settings: 'FILL' 0, 'wght' 400, 'GRAD' 0, 'opsz' 24;
        }
    </style>
</head>

<body class="bg-background font-body-md text-on-surface">
    <aside class="fixed left-0 top-0 hidden md:flex flex-col h-full py-4 bg-slate-50 w-64 border-r border-slate-200 z-50">
        <div class="px-6 mb-8">
            <h2 class="text-lg font-bold text-slate-900">Rewards Engine</h2>
            <p class="text-[10px] text-slate-500 font-bold uppercase tracking-widest">Enterprise Tier</p>
        </div>
        <nav class="flex-1 space-y-1">
            <a class="flex items-center px-6 py-3 bg-blue-50 text-blue-700 rounded-lg mx-2 transition-all duration-200 ease-in-out" href="#upload">
                <span class="material-symbols-outlined mr-3">upload_file</span>
                <span class="text-sm font-medium">Upload</span>
            </a>
            <a class="flex items-center px-6 py-3 text-slate-600 hover:text-blue-600 hover:bg-slate-100 rounded-lg mx-2 transition-all duration-200 ease-in-out" href="#results">
                <span class="material-symbols-outlined mr-3">receipt_long</span>
                <span class="text-sm font-medium">Resultados</span>
            </a>
            <a class="flex items-center px-6 py-3 text-slate-600 hover:text-blue-600 hover:bg-slate-100 rounded-lg mx-2 transition-all duration-200 ease-in-out" href="#rules">
                <span class="material-symbols-outlined mr-3">rule</span>
                <span class="text-sm font-medium">Regras</span>
            </a>
        </nav>
        <div class="mt-auto px-2 space-y-1">
            <button class="w-full flex items-center justify-center gap-2 bg-primary text-on-primary py-3 rounded-xl font-bold text-sm mb-4 transition-transform active:scale-95 shadow-lg shadow-primary/20" type="button">
                <span class="material-symbols-outlined">add</span>
                Nova Analise
            </button>
            <a class="flex items-center px-6 py-3 text-slate-600 hover:bg-slate-100 rounded-lg mx-2 transition-colors" href="#">
                <span class="material-symbols-outlined mr-3">settings</span>
                <span class="text-sm font-medium">Configuracoes</span>
            </a>
        </div>
    </aside>

    <main class="md:ml-64 min-h-screen">
        <header class="sticky top-0 z-50 flex justify-between items-center px-6 h-16 w-full bg-white/80 backdrop-blur-md border-b border-white/20 shadow-sm">
            <div class="flex items-center gap-4">
                <span class="text-xl font-black text-blue-700 tracking-tight">CashbackPro</span>
                <div class="h-4 w-[1px] bg-slate-300"></div>
                <h1 class="font-h3 text-h3 text-on-surface">Processing Center</h1>
            </div>
            <div class="flex items-center gap-4">
                <button class="flex items-center gap-2 bg-primary-container text-on-primary-container px-4 py-2 rounded-lg font-semibold hover:bg-primary transition-colors" type="button">
                    <span class="material-symbols-outlined text-lg">download</span>
                    Exportar Resultado
                </button>
                <button class="p-2 text-slate-500 hover:bg-slate-50 rounded-full transition-colors" type="button">
                    <span class="material-symbols-outlined">notifications</span>
                </button>
                <button class="p-2 text-slate-500 hover:bg-slate-50 rounded-full transition-colors" type="button">
                    <span class="material-symbols-outlined">help</span>
                </button>
            </div>
        </header>

        <div class="p-margin-page max-w-container-max mx-auto space-y-stack-lg">
            <section id="upload" class="grid grid-cols-1 lg:grid-cols-12 gap-gutter">
                <div class="lg:col-span-12">
                    <div class="bg-white rounded-xl border border-outline-variant p-card-padding shadow-sm hover:shadow-md transition-shadow">
                        <form action="processa" method="POST" enctype="multipart/form-data" class="flex flex-col md:flex-row items-center gap-gutter">
                            <div class="w-full md:w-2/3">
                                <label for="csvFile" class="js-drop-zone group block border-2 border-dashed border-primary/20 rounded-xl bg-surface-container-low p-8 text-center cursor-pointer hover:bg-surface-container transition-colors">
                                    <span class="material-symbols-outlined text-4xl text-primary mb-2 group-hover:scale-110 transition-transform">upload_file</span>
                                    <h3 class="font-h3 text-h3 mb-1">Upload de Transacoes</h3>
                                    <p class="text-on-surface-variant font-body-md">Arraste o CSV aqui ou clique para selecionar</p>
                                    <p class="text-xs text-outline mt-2">Maximo 50MB. Suporta .csv e .xlsx</p>
                                </label>
                                <input id="csvFile" name="arquivo" type="file" accept=".csv,.xlsx" class="hidden"/>
                            </div>
                            <div class="w-full md:w-1/3 flex flex-col gap-4">
                                <div class="p-4 bg-surface-container-high rounded-lg flex items-start gap-3">
                                    <span class="material-symbols-outlined text-primary">info</span>
                                    <div>
                                        <p class="font-bold text-sm">Sistema pronto</p>
                                        <p class="text-xs text-on-surface-variant">Ultima execucao: <c:out value="${ultimaExecucao}" default="-"/></p>
                                    </div>
                                </div>
                                <button class="w-full py-4 bg-primary text-on-primary rounded-xl font-bold hover:brightness-110 active:scale-[0.98] transition-all shadow-lg shadow-primary/30" type="submit">
                                    Iniciar Processamento
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </section>

            <section class="grid grid-cols-1 md:grid-cols-3 gap-gutter">
                <div class="bg-white p-card-padding rounded-xl border border-outline-variant shadow-sm flex items-center justify-between">
                    <div>
                        <p class="font-label-caps text-label-caps text-on-surface-variant mb-2">TOTAL LINHAS</p>
                        <p class="font-stat-value text-stat-value text-on-surface">
                            <c:out value="${totalLinhas}" default="0"/>
                        </p>
                    </div>
                    <div class="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center">
                        <span class="material-symbols-outlined text-primary">analytics</span>
                    </div>
                </div>
                <div class="bg-white p-card-padding rounded-xl border border-outline-variant shadow-sm flex items-center justify-between">
                    <div>
                        <p class="font-label-caps text-label-caps text-on-surface-variant mb-2">TOTAL CASHBACK</p>
                        <p class="font-stat-value text-stat-value text-primary">
                            <c:choose>
                                <c:when test="${not empty totalCashback}">
                                    R$ <fmt:formatNumber value="${totalCashback}" minFractionDigits="2"/>
                                </c:when>
                                <c:otherwise>R$ 0,00</c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                    <div class="w-12 h-12 rounded-full bg-green-500/10 flex items-center justify-center">
                        <span class="material-symbols-outlined text-green-600">payments</span>
                    </div>
                </div>
                <div class="bg-white p-card-padding rounded-xl border border-outline-variant shadow-sm flex items-center justify-between">
                    <div>
                        <p class="font-label-caps text-label-caps text-on-surface-variant mb-2">ERROS</p>
                        <p class="font-stat-value text-stat-value text-error">
                            <c:out value="${totalErros}" default="0"/>
                        </p>
                    </div>
                    <div class="w-12 h-12 rounded-full bg-error/10 flex items-center justify-center">
                        <span class="material-symbols-outlined text-error">warning</span>
                    </div>
                </div>
            </section>

            <section id="results" class="bg-white rounded-xl border border-outline-variant shadow-sm overflow-hidden">
                <div class="px-card-padding py-4 border-b border-outline-variant bg-surface-container-lowest flex justify-between items-center">
                    <h2 class="font-h3 text-h3">Resultados de Processamento</h2>
                    <div class="flex gap-3">
                        <div class="relative">
                            <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant text-sm">search</span>
                            <input class="pl-10 pr-4 py-2 border border-outline-variant rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary/20" placeholder="Buscar ID do cliente..." type="text"/>
                        </div>
                        <button class="flex items-center gap-2 px-4 py-2 border border-outline-variant rounded-lg text-sm font-semibold hover:bg-slate-50" type="button">
                            <span class="material-symbols-outlined text-sm">filter_list</span>
                            Filtrar
                        </button>
                    </div>
                </div>
                <div class="overflow-x-auto">
                    <table class="w-full text-left border-collapse">
                        <thead>
                            <tr class="bg-surface-container-low text-on-surface-variant">
                                <th class="px-6 py-4 font-label-caps text-label-caps">ID CLIENTE</th>
                                <th class="px-6 py-4 font-label-caps text-label-caps">NOME</th>
                                <th class="px-6 py-4 font-label-caps text-label-caps">TOTAL COMPRADO</th>
                                <th class="px-6 py-4 font-label-caps text-label-caps">CATEGORIA</th>
                                <th class="px-6 py-4 font-label-caps text-label-caps">CASHBACK FINAL</th>
                            </tr>
                        </thead>
                        <tbody class="divide-y divide-outline-variant">
                            <c:forEach var="cliente" items="${clientes}">
                                <tr class="hover:bg-surface-container-lowest transition-colors">
                                    <td class="px-6 py-4 font-mono text-sm">#${cliente.idCliente}</td>
                                    <td class="px-6 py-4 font-semibold">${cliente.nome}</td>
                                    <td class="px-6 py-4">
                                        R$ <fmt:formatNumber value="${cliente.totalComprado}" minFractionDigits="2"/>
                                    </td>
                                    <td class="px-6 py-4">
                                        <c:choose>
                                            <c:when test="${cliente.tier == 'PLATINUM'}">
                                                <span class="px-3 py-1 bg-slate-100 text-slate-700 rounded-full text-xs font-bold uppercase tracking-wider">Platinum</span>
                                            </c:when>
                                            <c:when test="${cliente.tier == 'GOLD'}">
                                                <span class="px-3 py-1 bg-yellow-100 text-yellow-800 rounded-full text-xs font-bold uppercase tracking-wider">Gold</span>
                                            </c:when>
                                            <c:when test="${cliente.tier == 'SILVER'}">
                                                <span class="px-3 py-1 bg-slate-100 text-slate-700 rounded-full text-xs font-bold uppercase tracking-wider">Silver</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="px-3 py-1 bg-surface-container-high text-on-surface-variant rounded-full text-xs font-bold uppercase tracking-wider">Normal</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="px-6 py-4 font-bold text-primary">
                                        R$ <fmt:formatNumber value="${cliente.cashbackTotal}" minFractionDigits="2"/>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty clientes}">
                                <tr>
                                    <td colspan="5" class="px-6 py-20 text-center text-slate-400">
                                        <span class="material-symbols-outlined text-5xl mb-4 block">upload_file</span>
                                        Nenhum dado processado. Envie um CSV para iniciar.
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <div class="px-card-padding py-4 bg-surface-container-lowest flex justify-between items-center border-t border-outline-variant">
                    <p class="text-sm text-on-surface-variant">
                        <c:choose>
                            <c:when test="${not empty totalLinhas}">Mostrando ${totalLinhas} resultados</c:when>
                            <c:otherwise>Mostrando 0 resultados</c:otherwise>
                        </c:choose>
                    </p>
                    <div class="flex gap-2">
                        <button class="p-2 border border-outline-variant rounded hover:bg-slate-50 disabled:opacity-50" disabled type="button">
                            <span class="material-symbols-outlined">chevron_left</span>
                        </button>
                        <button class="p-2 border border-outline-variant rounded hover:bg-slate-50" type="button">
                            <span class="material-symbols-outlined">chevron_right</span>
                        </button>
                    </div>
                </div>
            </section>

            <section id="rules" class="flex flex-col md:flex-row justify-between items-center gap-gutter pt-8">
                <div class="flex items-center gap-4">
                    <img alt="Data Visualization" class="w-16 h-16 rounded-xl object-cover shadow-lg" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBtEQkxHUff3q7hsO8vzXi4AE9HX5E4ZUjxxqsoeT30_BWB2NsfYEMGt_WOW2sF5goDXl7pLfuySj5bq1RCutvohSZo3Tbcg5v3VlejtosX87ljT1xNxZ6EPGufMcy1i29IZbCrY2OkeSJIZC6ng1N6dzOI98F3877C_uouMG6POAz4vnfAAhCEzLLihnT8PDO7tGPMgWIfEsGBTxiQo3bbxf79j5Hnetex51Z5at1ncRlTnzX4Yk-lgkX-KesV2k-ZjUpSu0KdTO77"/>
                    <div>
                        <h4 class="font-bold text-on-surface">Integridade confirmada</h4>
                        <p class="text-sm text-on-surface-variant">Calculos verificados pelo motor de regras</p>
                    </div>
                </div>
                <div class="flex gap-4">
                    <button class="px-8 py-3 bg-white border-2 border-primary text-primary rounded-xl font-bold hover:bg-primary/5 transition-colors" type="button">
                        Visualizar Relatorio
                    </button>
                    <button class="px-8 py-3 bg-primary text-on-primary rounded-xl font-bold hover:brightness-110 active:scale-95 transition-all shadow-xl shadow-primary/20 flex items-center gap-2" type="button">
                        <span class="material-symbols-outlined">download_for_offline</span>
                        Exportar CSV Consolidado
                    </button>
                </div>
            </section>
        </div>
    </main>

    <script>
        const dropZone = document.querySelector('.js-drop-zone');
        const fileInput = document.getElementById('csvFile');

        if (dropZone && fileInput) {
            ['dragenter', 'dragover', 'dragleave', 'drop'].forEach((eventName) => {
                dropZone.addEventListener(eventName, (event) => {
                    event.preventDefault();
                    event.stopPropagation();
                }, false);
            });

            ['dragenter', 'dragover'].forEach((eventName) => {
                dropZone.addEventListener(eventName, () => {
                    dropZone.classList.add('bg-primary/10');
                    dropZone.classList.add('border-primary');
                }, false);
            });

            ['dragleave', 'drop'].forEach((eventName) => {
                dropZone.addEventListener(eventName, () => {
                    dropZone.classList.remove('bg-primary/10');
                    dropZone.classList.remove('border-primary');
                }, false);
            });

            dropZone.addEventListener('drop', (event) => {
                const files = event.dataTransfer.files;
                if (files && files.length > 0) {
                    const dataTransfer = new DataTransfer();
                    dataTransfer.items.add(files[0]);
                    fileInput.files = dataTransfer.files;
                }
            });
        }
    </script>
</body>
</html>