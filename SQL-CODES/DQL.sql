-- SELECIONANDO TODOS OS DADOS DA TABELA CLIENTE
SELECT * FROM cliente;

-- SELECIONANDO TODOS OS DADOS DA TABELA ADMIN

SELECT * FROM admin;

-- SELECIONANDO TODOS OS DADOS DA TABELA SOLIAGRENDA
SELECT * FROM public.soliagenda;

-- SELECIONANDO TODOS OS DADOS DA TABELA AGENDA
SELECT * FROM public.agenda;

-- Informações Gerais Sobre: Data agendamento para os que foram confirmados

SELECT 
  clt.nome AS nclt,
  soliagd.dtsoli,
  agd.diasemana AS dsmn,
  agd.dthagenda AS dthagd,
  adm.nome AS nadm,
  soliagd.confirmsoli,
  soliagd.descricao
FROM 
  public.soliagenda AS soliagd
    INNER JOIN public.cliente AS clt ON clt.idcliente = soliagd.idcliente
    INNER JOIN public.admin AS adm ON adm.idadmin = soliagd.idadmin
    INNER JOIN public.agenda AS agd ON agd.idsoli = soliagd.idsoli
WHERE 
    confirmsoli  = TRUE;



-- Informações gerais dos Agendamentos que ainda não foram confirmados
SELECT 
  clt.nome AS nclt,
  soliagd.dtsoli,
  agd.diasemana AS dsmn,
  agd.dthagenda AS dthagd,
  adm.nome AS nadm,
  soliagd.confirmsoli,
  soliagd.descricao
FROM 
  public.soliagenda AS soliagd
    INNER JOIN public.cliente AS clt ON clt.idcliente = soliagd.idcliente
    INNER JOIN public.admin AS adm ON adm.idadmin = soliagd.idadmin
    INNER JOIN public.agenda AS agd ON agd.idsoli = soliagd.idsoli
WHERE 
    confirmsoli  = FALSE;


-- Informações gerais de Agendamentos que foram 
SELECT 
  clt.nome AS nclt,
  soliagd.dtsoli,
  agd.diasemana AS dsmn,
  agd.dthagenda AS dthagd,
  adm.nome AS nadm,
  soliagd.confirmsoli
FROM 
  public.soliagenda AS soliagd
    INNER JOIN public.cliente AS clt ON clt.idcliente = soliagd.idcliente
    INNER JOIN public.admin AS adm ON adm.idadmin = soliagd.idadmin
    INNER JOIN public.agenda AS agd ON agd.idsoli = soliagd.idsoli
WHERE 
    soliagd.confirmsoli IS NULL;


-- Contabilizando pessoas atendidas, não atendidadas que ainda estão por atender