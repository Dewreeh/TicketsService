--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-04-09 02:59:02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 226 (class 1259 OID 25757)
-- Name: bought_tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bought_tickets (
    id integer,
    route_id integer,
    date_time timestamp without time zone,
    seat_number integer,
    price numeric(10,2)
);


ALTER TABLE public.bought_tickets OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25698)
-- Name: carriers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.carriers (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    phone character varying(20) NOT NULL
);


ALTER TABLE public.carriers OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25697)
-- Name: carriers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.carriers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.carriers_id_seq OWNER TO postgres;

--
-- TOC entry 4899 (class 0 OID 0)
-- Dependencies: 217
-- Name: carriers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.carriers_id_seq OWNED BY public.carriers.id;


--
-- TOC entry 220 (class 1259 OID 25705)
-- Name: routes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.routes (
    id integer NOT NULL,
    departure_point character varying(255) NOT NULL,
    destination_point character varying(255) NOT NULL,
    carrier_id integer,
    duration_minutes integer NOT NULL
);


ALTER TABLE public.routes OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25704)
-- Name: routes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.routes_id_seq OWNER TO postgres;

--
-- TOC entry 4900 (class 0 OID 0)
-- Dependencies: 219
-- Name: routes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.routes_id_seq OWNED BY public.routes.id;


--
-- TOC entry 222 (class 1259 OID 25719)
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id integer NOT NULL,
    route_id integer,
    date_time timestamp without time zone NOT NULL,
    seat_number integer NOT NULL,
    price numeric(10,2) NOT NULL
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25718)
-- Name: tickets_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tickets_id_seq OWNER TO postgres;

--
-- TOC entry 4901 (class 0 OID 0)
-- Dependencies: 221
-- Name: tickets_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_seq OWNED BY public.tickets.id;


--
-- TOC entry 225 (class 1259 OID 25741)
-- Name: user_tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_tickets (
    user_id integer NOT NULL,
    ticket_id integer NOT NULL
);


ALTER TABLE public.user_tickets OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25731)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    role character varying(10) DEFAULT 'USER'::character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25730)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 4902 (class 0 OID 0)
-- Dependencies: 223
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 4718 (class 2604 OID 25701)
-- Name: carriers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carriers ALTER COLUMN id SET DEFAULT nextval('public.carriers_id_seq'::regclass);


--
-- TOC entry 4719 (class 2604 OID 25708)
-- Name: routes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.routes ALTER COLUMN id SET DEFAULT nextval('public.routes_id_seq'::regclass);


--
-- TOC entry 4720 (class 2604 OID 25722)
-- Name: tickets id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id SET DEFAULT nextval('public.tickets_id_seq'::regclass);


--
-- TOC entry 4721 (class 2604 OID 25734)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 4893 (class 0 OID 25757)
-- Dependencies: 226
-- Data for Name: bought_tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bought_tickets (id, route_id, date_time, seat_number, price) FROM stdin;
44	1	2025-04-15 14:30:00	42	5000.50
45	1	2025-04-15 14:30:00	43	5000.50
46	1	2025-04-15 14:30:00	25	5000.50
47	2	2025-04-15 14:30:00	25	5000.50
48	3	2025-04-15 14:30:00	25	5000.50
\.


--
-- TOC entry 4885 (class 0 OID 25698)
-- Dependencies: 218
-- Data for Name: carriers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.carriers (id, name, phone) FROM stdin;
1	Аэрофлот	+7 495 223-55-55
2	РЖД	+7 800 775-00-00
3	LuxExpress	+372 680 0909
\.


--
-- TOC entry 4887 (class 0 OID 25705)
-- Dependencies: 220
-- Data for Name: routes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.routes (id, departure_point, destination_point, carrier_id, duration_minutes) FROM stdin;
1	Москва	Санкт-Петербург	1	90
2	Москва	Сочи	1	120
3	Санкт-Петербург	Хельсинки	3	180
4	Москва	Казань	2	480
5	Екатеринбург	Новосибирск	2	300
\.


--
-- TOC entry 4889 (class 0 OID 25719)
-- Dependencies: 222
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tickets (id, route_id, date_time, seat_number, price) FROM stdin;
44	1	2025-04-15 14:30:00	42	5000.50
45	1	2025-04-15 14:30:00	43	5000.50
46	1	2025-04-15 14:30:00	25	5000.50
47	2	2025-04-15 14:30:00	25	5000.50
48	3	2025-04-15 14:30:00	25	5000.50
49	1	2025-04-18 14:30:00	25	5000.50
50	1	2025-04-18 14:30:00	25	3000.50
51	1	2025-04-18 14:30:00	70	3000.50
52	4	2025-04-18 14:30:00	70	3000.50
53	5	2025-04-18 14:30:00	70	3000.50
\.


--
-- TOC entry 4892 (class 0 OID 25741)
-- Dependencies: 225
-- Data for Name: user_tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_tickets (user_id, ticket_id) FROM stdin;
35	44
35	45
35	46
35	47
35	48
\.


--
-- TOC entry 4891 (class 0 OID 25731)
-- Dependencies: 224
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, password, full_name, role) FROM stdin;
35	USER1	$2a$10$yQ8hy55Rx1Eo.7mLbTK/3uhJNW.QQ0DMwXqzGiBQaArD4SSzYJbvq	Антон	USER
36	USER2	$2a$10$SklL35WvQArSs3AuqIkpVumIipL/V2FVB5P0/0PPvu1nlcwby63mS	Максим	USER
34	ADMIN	$2a$10$myiDbM6yRxtjGFcJ7DROue76Q.tEemUoeuZXebLr8CiLMnH3uZdLa	Админ Админович	ADMIN
\.


--
-- TOC entry 4903 (class 0 OID 0)
-- Dependencies: 217
-- Name: carriers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.carriers_id_seq', 1, false);


--
-- TOC entry 4904 (class 0 OID 0)
-- Dependencies: 219
-- Name: routes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.routes_id_seq', 1, false);


--
-- TOC entry 4905 (class 0 OID 0)
-- Dependencies: 221
-- Name: tickets_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_seq', 53, true);


--
-- TOC entry 4906 (class 0 OID 0)
-- Dependencies: 223
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 37, true);


--
-- TOC entry 4724 (class 2606 OID 25703)
-- Name: carriers carriers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.carriers
    ADD CONSTRAINT carriers_pkey PRIMARY KEY (id);


--
-- TOC entry 4726 (class 2606 OID 25712)
-- Name: routes routes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.routes
    ADD CONSTRAINT routes_pkey PRIMARY KEY (id);


--
-- TOC entry 4728 (class 2606 OID 25724)
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);


--
-- TOC entry 4734 (class 2606 OID 25745)
-- Name: user_tickets user_tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_tickets
    ADD CONSTRAINT user_tickets_pkey PRIMARY KEY (user_id, ticket_id);


--
-- TOC entry 4730 (class 2606 OID 25740)
-- Name: users users_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_login_key UNIQUE (login);


--
-- TOC entry 4732 (class 2606 OID 25738)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4735 (class 2606 OID 25713)
-- Name: routes routes_carrier_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.routes
    ADD CONSTRAINT routes_carrier_id_fkey FOREIGN KEY (carrier_id) REFERENCES public.carriers(id);


--
-- TOC entry 4736 (class 2606 OID 25725)
-- Name: tickets tickets_route_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_route_id_fkey FOREIGN KEY (route_id) REFERENCES public.routes(id);


--
-- TOC entry 4737 (class 2606 OID 25751)
-- Name: user_tickets user_tickets_ticket_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_tickets
    ADD CONSTRAINT user_tickets_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(id);


--
-- TOC entry 4738 (class 2606 OID 25746)
-- Name: user_tickets user_tickets_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_tickets
    ADD CONSTRAINT user_tickets_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2025-04-09 02:59:05

--
-- PostgreSQL database dump complete
--

