# AI-Optimization

Adaptação:

O algoritmo foi projetado para se adaptar à uma função genérica, possuindo um domínio de N dimensões (Onde N>=1), embora quanto maior seja o número de dimensões, menos preciso se torne o algoritmo.

Ele encontra o ponto mínimo de uma função, o que também é adaptável para encontrar o seu ponto máximo. Basta mudar a classe 'ComparadorDeY'.

Para mudar a função, basta editar a função 'calculaFuncao', que retorna a imagem da sua função, dado um vetor de entrada, representando as variáveis da função. Deve-se mudar também a variável 'qtd_x', que representa quantas variáveis a função possui. Os veteores xIni e xFin devem receber o valor mínimo e o valor máximo que as variáveis da sua função podem assumir, o 'qtd_iteracoes' recebe a quantidade de iterações que o algoritmo terá e 'divisoes' recebe quantas divisões serão feitas no intervalo de valores que cada variável pode assumir.

Funcionamento:

O algoritmo funciona basicamente da seguinte forma, o intervalo de cada variável é dividido em D valores (representando a variável 'divisoes') e esses valores são colocados nas variáveis da população inicial de Pais. É feito um cruzamento desses pais e uma mutação deles. A mutação é feita adicionando um valor a cada uma das variáveis, este valor é o desvio padrão atual que a população de Pais possui. Por fim, é feito uma ordenação crescente com toda população e os melhores valores das variáveis (que possuem menores imagens) são passados para a próxima geração, assumindo a próxima população de Pais. A cada iteração, é exibido o ponto da função que possui menor imagem.

Teste:

Um dos testes foi feito com a função f(x,y) = x^2 + y^2 + (3*x + 4*y -26)^2 onde x assume o intervalo fechado [0,100] e y o intervalo [0,50].
O melhor resultado foi encontrado na 17ª iteração, com x=2.9999683 e y=4.000258. Sendo que o ponto mínimo desta função é o (3,4) (Sim, a precisão ainda pode melhorar!).

