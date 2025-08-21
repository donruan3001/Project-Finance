package finance.domain.transactions;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoryTransactions {
    // 🏠 Despesas Fixas / Essenciais
    MORADIA,
    TRANSPORTE,
    ALIMENTACAO,

    // 🍽️ Despesas Variáveis
    RESTAURANTES,
    DELIVERY,

    // 💳 Financeiro
    PARCELAS,
    JUROS_MULTAS,
    TARIFAS_BANCARIAS,
    EMPRESTIMOS,

    // 💼 Trabalho / Negócios
    MATERIAIS_ESCRITORIO,
    SOFTWARES,
    CURSOS_TREINAMENTOS,

    // 🎯 Pessoais
    ROUPAS,
    BELEZA,
    HIGIENE,

    // 🏥 Saúde
    PLANO_SAUDE,
    CONSULTAS,
    FARMACIA,

    // 🎉 Lazer
    CINEMA,
    SHOWS,
    VIAGENS,
    STREAMING,

    // 🎓 Educação
    MENSALIDADE_ESCOLAR,
    CURSOS_ONLINE,
    LIVROS,

    // 🎁 Presentes e Doações
    PRESENTES,
    DOACOES,

    // 🐾 Pets
    ALIMENTACAO_PET,
    VETERINARIO;
    @JsonCreator
    public static CategoryTransactions fromString(String value) {
        return CategoryTransactions.valueOf(value.toUpperCase());
    }

}
