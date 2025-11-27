# 🐛 Issues e Sugestões de Melhorias

Este documento lista problemas identificados no código e sugestões de melhorias para o projeto.

---

## 🔴 Issues Críticas (Alta Prioridade)

### 1. Erro de Sintaxe no GlobalExceptionHandler
**Arquivo:** `src/main/java/finance/exceptions/GlobalExceptionHandler.java`  
**Linha:** 79  
**Problema:** Método `handleIllegalArgumentException` está incompleto - falta a assinatura completa do método.

```java
// Linha 79 - Código atual está quebrado
@ExceptionHandler(IllegalArgumentException.class)
public
    logger.warning("Illegal argument exception: " + e);
```

**Solução:**
```java
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
    logger.warning("Illegal argument exception: " + e);
    Map<String, Object> response = new HashMap<>();
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Invalid Argument");
    response.put("message", e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
```

**Prioridade:** 🔴 CRÍTICA - O código não compila

---

### 2. Inconsistência nas Rotas da API
**Problema:** Alguns endpoints usam `/api/v1/` e outros não, causando inconsistência.

**Exemplos:**
- `/api/v1/transactions` (ControllerTransactions)
- `/accounts` (ControllerAccount)
- `/banks` (ControllerBank)
- `/auth` (ControllerAuth)

**Solução:** Padronizar todas as rotas com versionamento:
- `/api/v1/auth/*`
- `/api/v1/accounts/*`
- `/api/v1/banks/*`
- `/api/v1/transactions/*`
- `/api/v1/admin/*`

**Prioridade:** 🟡 MÉDIA

---

### 3. Falta de Endpoints GET para Transações
**Problema:** Não existe endpoint para listar transações do usuário.

**Arquivo:** `src/main/java/finance/controlers/ControllerTransactions.java`

**Solução:** Adicionar endpoints:
```java
@GetMapping
public ResponseEntity<Page<TransactionResponseDTO>> getMyTransactions(
    @PageableDefault(size = 20, page = 0) Pageable pageable
) {
    // Implementar
}

@GetMapping("/{id}")
public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Long id) {
    // Implementar
}
```

**Prioridade:** 🟡 MÉDIA

---

### 4. Falta de Segurança em Endpoints de Transações e Contas
**Problema:** Endpoints não estão protegidos com autenticação obrigatória.

**Solução:** Adicionar `@PreAuthorize("isAuthenticated()")` ou configurar no SecurityConfiguration.

**Prioridade:** 🔴 ALTA - Segurança

---

### 5. Uso Inconsistente de Injeção de Dependência
**Problema:** Mistura de `@Autowired` e `@RequiredArgsConstructor`.

**Exemplo:**
```java
@RequiredArgsConstructor  // Linha 17
public class ControllerAccount {
    @Autowired  // Linha 20 - redundante
    private ServiceAccount serviceAccount;
}
```

**Solução:** Usar apenas `@RequiredArgsConstructor` com `final` nos campos.

**Prioridade:** 🟢 BAIXA

---

## 🟡 Issues de Funcionalidade (Média Prioridade)

### 6. Falta de Filtros e Busca para Transações
**Problema:** Não é possível filtrar transações por data, categoria, tipo, valor, etc.

**Sugestão:** Implementar endpoints com Query Parameters:
```java
@GetMapping
public ResponseEntity<Page<TransactionResponseDTO>> getTransactions(
    @RequestParam(required = false) LocalDate startDate,
    @RequestParam(required = false) LocalDate endDate,
    @RequestParam(required = false) CategoryTransactions category,
    @RequestParam(required = false) TypeTransaction type,
    @PageableDefault(size = 20) Pageable pageable
)
```

**Prioridade:** 🟡 MÉDIA

---

### 7. Falta de Validação de Saldo ao Criar Transação
**Problema:** Não há validação para impedir transações que deixem o saldo negativo (se aplicável).

**Sugestão:** Adicionar validação no `ServiceTransactions`:
```java
if (transaction.getType() == TypeTransaction.DESPESA) {
    if (account.getBalance().compareTo(transaction.getAmount()) < 0) {
        throw new InsufficientBalanceException("Saldo insuficiente");
    }
}
```

**Prioridade:** 🟡 MÉDIA

---

### 8. Falta de Endpoint para Atualizar Transações
**Problema:** Não é possível editar ou deletar transações.

**Sugestão:** Adicionar:
- `PUT /api/v1/transactions/{id}` - Atualizar transação
- `DELETE /api/v1/transactions/{id}` - Deletar transação

**Prioridade:** 🟡 MÉDIA

---

### 9. Falta de Endpoint para Atualizar Conta
**Problema:** Usuários não podem atualizar suas próprias contas (apenas admin pode).

**Sugestão:** Adicionar `PUT /accounts/{id}` para usuários autenticados atualizarem suas próprias contas.

**Prioridade:** 🟡 MÉDIA

---

### 10. Falta de Recuperação de Senha
**Problema:** Não existe funcionalidade de "esqueci minha senha".

**Sugestão:** Implementar:
- `POST /auth/forgot-password` - Enviar email com token
- `POST /auth/reset-password` - Redefinir senha com token

**Prioridade:** 🟡 MÉDIA

---

### 11. Falta de Refresh Token
**Problema:** JWT não tem mecanismo de refresh, forçando novo login quando expira.

**Sugestão:** Implementar refresh token para melhorar UX.

**Prioridade:** 🟡 MÉDIA

---

## 🟢 Melhorias de Qualidade de Código

### 12. Melhorar Tratamento de Erros
**Problema:** Alguns handlers retornam o objeto Exception completo ao invés da mensagem.

**Exemplo:**
```java
response.put("message", e);  // Retorna objeto, não mensagem
```

**Solução:** Usar `e.getMessage()` consistentemente.

**Prioridade:** 🟢 BAIXA

---

### 13. Adicionar Logging Mais Detalhado
**Problema:** Logs são muito genéricos.

**Sugestão:** Usar SLF4J/Logback ao invés de `java.util.logging` e adicionar mais contexto:
```java
logger.warn("User not found: username={}", username, e);
```

**Prioridade:** 🟢 BAIXA

---

### 14. Padronizar Respostas de Erro
**Problema:** Alguns endpoints retornam `ProblemDetail`, outros retornam `Map<String, Object>`.

**Sugestão:** Criar uma classe `ErrorResponse` padronizada ou usar apenas `ProblemDetail`.

**Prioridade:** 🟢 BAIXA

---

### 15. Adicionar Validações de Negócio
**Problema:** Falta validação de regras de negócio (ex: não permitir deletar banco com contas vinculadas).

**Sugestão:** Adicionar validações nos services antes de operações destrutivas.

**Prioridade:** 🟡 MÉDIA

---

## 🎨 Melhorias de Frontend

### 16. Falta de Interceptor HTTP para JWT
**Problema:** Token não é enviado automaticamente em todas as requisições.

**Sugestão:** Criar `JwtInterceptor`:
```typescript
intercept(req: HttpRequest<any>, next: HttpHandler) {
  const token = this.authService.getToken();
  if (token) {
    req = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }
  return next.handle(req);
}
```

**Prioridade:** 🔴 ALTA

---

### 17. Falta de Guards de Rota
**Problema:** Rotas não estão protegidas no frontend.

**Sugestão:** Criar `AuthGuard` para proteger rotas que requerem autenticação.

**Prioridade:** 🔴 ALTA

---

### 18. Melhorar Tratamento de Erros no Frontend
**Problema:** Erros da API não são tratados adequadamente.

**Sugestão:** Criar um serviço de tratamento de erros global e exibir mensagens amigáveis ao usuário.

**Prioridade:** 🟡 MÉDIA

---

### 19. Adicionar Loading States
**Problema:** Não há feedback visual durante carregamento.

**Sugestão:** Adicionar spinners/loading indicators em operações assíncronas.

**Prioridade:** 🟡 MÉDIA

---

### 20. Validação de Formulários
**Problema:** Validação de formulários pode ser melhorada.

**Sugestão:** Usar Angular Reactive Forms com validações customizadas.

**Prioridade:** 🟡 MÉDIA

---

## 🚀 Melhorias de Arquitetura e Performance

### 21. Adicionar Cache
**Sugestão:** Implementar cache para dados que mudam pouco (ex: lista de bancos).

**Prioridade:** 🟢 BAIXA

---

### 22. Adicionar Rate Limiting
**Sugestão:** Implementar rate limiting para prevenir abuso da API.

**Prioridade:** 🟡 MÉDIA

---

### 23. Adicionar Paginação Consistente
**Problema:** Nem todos os endpoints que retornam listas têm paginação.

**Sugestão:** Padronizar paginação em todos os endpoints de listagem.

**Prioridade:** 🟡 MÉDIA

---

### 24. Adicionar Soft Delete
**Problema:** Deletar registros remove permanentemente do banco.

**Sugestão:** Implementar soft delete para manter histórico.

**Prioridade:** 🟢 BAIXA

---

### 25. Adicionar Auditoria
**Sugestão:** Implementar auditoria para rastrear quem criou/modificou registros.

**Prioridade:** 🟢 BAIXA

---

## 📊 Melhorias de Funcionalidades de Negócio

### 26. Relatórios e Gráficos
**Sugestão:** Adicionar endpoints para:
- Relatório de receitas vs despesas por período
- Gráficos de gastos por categoria
- Projeção de saldo futuro

**Prioridade:** 🟢 BAIXA

---

### 27. Exportação de Dados
**Sugestão:** Permitir exportar transações em CSV/PDF.

**Prioridade:** 🟢 BAIXA

---

### 28. Metas e Orçamentos
**Sugestão:** Adicionar funcionalidade de definir metas de gastos e orçamentos por categoria.

**Prioridade:** 🟢 BAIXA

---

### 29. Categorias Customizadas
**Problema:** Categorias parecem ser fixas (enum).

**Sugestão:** Permitir que usuários criem suas próprias categorias.

**Prioridade:** 🟡 MÉDIA

---

### 30. Transferências Entre Contas
**Sugestão:** Adicionar funcionalidade de transferência entre contas do mesmo usuário.

**Prioridade:** 🟡 MÉDIA

---

## 🧪 Melhorias de Testes

### 31. Adicionar Testes Unitários
**Problema:** Poucos ou nenhum teste unitário.

**Sugestão:** Adicionar testes para:
- Services
- Controllers
- Validações de negócio

**Prioridade:** 🟡 MÉDIA

---

### 32. Adicionar Testes de Integração
**Sugestão:** Testes de integração para fluxos completos.

**Prioridade:** 🟡 MÉDIA

---

### 33. Adicionar Testes E2E
**Sugestão:** Testes end-to-end com Cypress ou similar.

**Prioridade:** 🟢 BAIXA

---

## 📝 Melhorias de Documentação

### 34. Melhorar Documentação da API
**Sugestão:** Adicionar mais exemplos e descrições detalhadas no Swagger.

**Prioridade:** 🟢 BAIXA

---

### 35. Adicionar Comentários Javadoc
**Sugestão:** Adicionar Javadoc em classes e métodos públicos.

**Prioridade:** 🟢 BAIXA

---

## 🔧 Melhorias de DevOps

### 36. Adicionar CI/CD
**Sugestão:** Implementar pipeline CI/CD (GitHub Actions, GitLab CI, etc.).

**Prioridade:** 🟡 MÉDIA

---

### 37. Adicionar Health Checks
**Sugestão:** Melhorar endpoints de health check do Actuator.

**Prioridade:** 🟢 BAIXA

---

### 38. Variáveis de Ambiente
**Problema:** Credenciais hardcoded no código.

**Sugestão:** Usar variáveis de ambiente para todas as configurações sensíveis.

**Prioridade:** 🔴 ALTA - Segurança

---

### 39. Dockerfile Multi-stage Otimizado
**Sugestão:** Otimizar Dockerfile para reduzir tamanho da imagem.

**Prioridade:** 🟢 BAIXA

---

### 40. Adicionar Dockerfile para Frontend
**Sugestão:** Criar Dockerfile e adicionar frontend ao docker-compose.

**Prioridade:** 🟡 MÉDIA

---

## 📋 Resumo de Prioridades

### 🔴 Críticas (Fazer Imediatamente)
1. Corrigir erro de sintaxe no GlobalExceptionHandler
2. Adicionar segurança em endpoints
3. Adicionar interceptor HTTP para JWT no frontend
4. Adicionar guards de rota no frontend
5. Mover credenciais para variáveis de ambiente

### 🟡 Médias (Fazer em Breve)
- Padronizar rotas da API
- Adicionar endpoints GET para transações
- Implementar filtros e busca
- Adicionar validação de saldo
- Implementar refresh token
- Adicionar testes
- Melhorar tratamento de erros no frontend

### 🟢 Baixas (Melhorias Futuras)
- Relatórios e gráficos
- Exportação de dados
- Cache e performance
- Documentação adicional

---

**Última atualização:** $(date)

