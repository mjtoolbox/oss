-- Revision: 1.2
-- Date: Aug 26, 2019
-- Todo: FK constraints are not defined yet
----------------------------------------------------
-- Change Log --------------------------------------
----------------------------------------------------
-- 1.4 Merge Guardian Guardian_Contact
-- 1.3 Term-Program join table created as it turns out m:m between term:program
-- 1.2 Foreign key columns added. No constraint added.
-- 1.1 Additional columns in Teacher table

----------------------------------------------------
-- DEFINE SEQUENCE, TABLE, PRIMARY KEY CONSTRAINT --
----------------------------------------------------


-------------
-- GUARDIAN --
-------------
-- Create Sequence. Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.guardian_guardian_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.guardian (
    guardian_id integer NOT NULL DEFAULT nextval('guardian_guardian_id_seq'),
    guardian_name character varying(50) NOT NULL, -- raw data null 
    relationship character varying(50),
    cell_phone character varying(12),
    email character varying(50),
    home_phone character varying(12),
    address character varying(50),
    city character varying(25),
    province character varying(2),
    postal_code character varying(7),
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT guardian_id_pk PRIMARY KEY (guardian_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.guardian OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when guardian table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.guardian_guardian_id_seq OWNED BY public.guardian.guardian_id;
-- END OF GUARDIAN --




-------------
-- STUDENT --
-------------
-- Create Sequence. Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.student_membership_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.student (
    membership_id integer NOT NULL DEFAULT nextval('student_membership_id_seq'),
    guardian_id integer NOT NULL,
    preferred_name character varying(50) NOT NULL,
    legal_name character varying(50) NOT NULL, -- raw data null 
    date_of_birth DATE NOT NULL,
    gender character varying(6) NOT NULL,
    membership_type character varying(20) NOT NULL, -- student, former student, trainer, intern
    grade integer NOT NULL, -- raw data null
    date_of_registration DATE NOT NULL,
    school character varying(50),
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT student_id_pk PRIMARY KEY (membership_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.student OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.student_membership_id_seq OWNED BY public.student.membership_id;
-- END OF STUDENT --

---------------------
-- STUDENT CONTACT --
---------------------
-- Create Sequence
CREATE SEQUENCE public.student_contact_student_contact_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- Create Table
CREATE TABLE public.student_contact (
    student_contact_id integer NOT NULL DEFAULT nextval('student_contact_student_contact_id_seq'),
    membership_id integer NOT NULL,
    cell_phone character varying(12),
    email character varying(50),
    home_phone character varying(12),
    address character varying(50),
    city character varying(25),
    province character varying(2),
    postal_code character varying(7),
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT student_contact_id_pk PRIMARY KEY (student_contact_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.student_contact OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.student_contact_student_contact_id_seq OWNED BY public.student_contact.student_contact_id;
-- END OF STUDENT_CONTACT --


------------------
-- TEACHER --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.teacher_teacher_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.teacher (
    teacher_id integer NOT NULL DEFAULT nextval('teacher_teacher_id_seq'),
    program_id integer NOT NULL,
    teacher_name character varying(50) NOT NULL, -- raw data null 
    cell_phone character varying(12) NOT NULL,
    email character varying(50) NOT NULL,
    home_phone character varying(12),
    address character varying(50) NOT NULL,
    city character varying(25) NOT NULL,
    province character varying(2) NOT NULL,
    postal_code character varying(7) NOT NULL,
    subject character varying(50) NOT NULL, -- Program type: coding, math, english, mechatronics
    status character varying(12) NOT NULL DEFAULT 'active', -- active, terminated, retired, leave of absent (lob)
    level integer NOT NULL,
    start_date date NOT NULL,
    end_date date,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT teacher_id_pk PRIMARY KEY (teacher_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.teacher OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.teacher_teacher_id_seq OWNED BY public.teacher.teacher_id;
-- END OF TEACHER --


------------------
-- PAYROLL --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.payroll_payroll_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.payroll (
    payroll_id integer NOT NULL DEFAULT nextval('payroll_payroll_id_seq'),
    teacher_id integer NOT NULL,
    hourly_rate integer NOT NULL,
    payment numeric,
    payment_date date NOT NULL,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT payroll_id_pk PRIMARY KEY (payroll_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.payroll OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.payroll_payroll_id_seq OWNED BY public.payroll.payroll_id;
-- END OF PAYROLL --



------------------
-- TIMESHEET --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.timesheet_timesheet_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.timesheet (
    timesheet_id integer NOT NULL DEFAULT nextval('timesheet_timesheet_id_seq'),
    payroll_id integer NOT NULL,
    teacher_id integer NOT NULL,
    ts_start date NOT NULL,
    ts_end date NOT NULL,
    hours_worked integer NOT NULL,
    submitted_date date,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT timesheet_id_pk PRIMARY KEY (timesheet_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.timesheet OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.timesheet_timesheet_id_seq OWNED BY public.timesheet.timesheet_id;
-- END OF PAYROLL --




------------------
-- TERM --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.term_term_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.term (
    term_id integer NOT NULL DEFAULT nextval('term_term_id_seq'),
    membership_id integer NOT NULL,
    total_cost numeric,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT term_id_pk PRIMARY KEY (term_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.term OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.term_term_id_seq OWNED BY public.term.term_id;
-- END OF TERM --




------------------
-- PROGAM --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.program_program_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.program (
    program_id integer NOT NULL DEFAULT nextval('program_program_id_seq'),
    subject character varying(50) NOT NULL,
    cost integer,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT program_id_pk PRIMARY KEY (program_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.program OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.program_program_id_seq OWNED BY public.program.program_id;
-- END OF PROGAM --



-----------------------------
-- TERMPROGRAM: Join table --
-----------------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.termprogram_termprogram_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.termprogram (
    termprogram_id integer NOT NULL DEFAULT nextval('termprogram_termprogram_id_seq'),
    term_id integer NOT NULL,
    program_id integer NOT NULL,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT termprogram_id_pk PRIMARY KEY (termprogram_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.termprogram OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.termprogram_termprogram_id_seq OWNED BY public.termprogram.program_id;
-- END OF PROGAM --


------------------
-- LESSON --
------------------
-- The lesson table is UNIQUE to debate students only; math students will only be registered in the math program

--- Create Sequence
CREATE SEQUENCE public.lesson_lesson_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do

-- Create Table
CREATE TABLE public.lesson (
    lesson_id integer NOT NULL DEFAULT nextval('lesson_lesson_id_seq'),
    teacher_id integer, -- To be able to save Lesson first and assign Teacher later
    program_id integer NOT NULL,
    name character varying(25) NOT NULL,
    season character varying(50) NOT NULL,
    lesson_date DATE NOT NULL,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT lesson_id_pk PRIMARY KEY (lesson_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.lesson OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.lesson_lesson_id_seq OWNED BY public.lesson.lesson_id;
-- END OF LESSON --


------------------
-- REPORT_CARD --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.report_card_report_card_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.report_card (
    report_card_id integer NOT NULL DEFAULT nextval('report_card_report_card_id_seq'),
    lesson_id integer NOT NULL,
    membership_id integer NOT NULL,
    teacher_id integer NOT NULL,
    score integer NOT NULL,
    interim_report jsonb, 
    report bytea NOT NULL,
    submitted_date date,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT report_card_id_pk PRIMARY KEY (report_card_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.report_card OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.report_card_report_card_id_seq OWNED BY public.report_card.report_card_id;
-- END OF PAYROLL --



------------------
-- INVOICE --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.invoice_invoice_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.invoice (
    invoice_number integer NOT NULL DEFAULT nextval('invoice_invoice_id_seq'),
    term_id integer NOT NULL,
    invoice_date date,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT invoice_id_pk PRIMARY KEY (invoice_number)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.invoice OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.invoice_invoice_id_seq OWNED BY public.invoice.invoice_number;
-- END OF INVOICE --


------------------
-- PAYMENT_TRANSACTION --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do
CREATE SEQUENCE public.pay_transaction_pay_transaction_id_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.pay_transaction (
    transaction_id integer NOT NULL DEFAULT nextval('pay_transaction_pay_transaction_id_seq'),
    invoice_number integer NOT NULL,
    method character varying(50) NOT NULL,
    result boolean,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT pay_transaction_id_pk PRIMARY KEY (transaction_id)
);

-- Alter Table Ownder to postgres
ALTER TABLE public.pay_transaction OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
ALTER SEQUENCE public.pay_transaction_pay_transaction_id_seq OWNED BY public.pay_transaction.transaction_id;
-- END OF PAYMENT_TRANSACTION --

------------------
--- USER ---------
------------------
CREATE SEQUENCE public.user_user_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.user (
    user_id integer NOT NULL DEFAULT nextval('user_user_id_seq'),
    username character varying(50) NOT NULL,
    name character varying(50) NOT NULL, -- raw data null 
    password character varying(200) NOT NULL,
    enabled boolean not null,
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT user_id_pk PRIMARY KEY (user_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.user OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.user_user_id_seq OWNED BY public.user.user_id;


-- CREATE TABLE public.users(
--     username character varying(50) NOT NULL PRIMARY KEY,
--     password character varying(200) NOT NULL,
--     enabled boolean not null
-- );


------------------
--- ROLE ---------
------------------
CREATE SEQUENCE public.role_role_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create Table
CREATE TABLE public.role (
    role_id integer NOT NULL DEFAULT nextval('role_role_id_seq'),
    authority character varying(50) NOT NULL, -- Role Name
    last_update timestamp without time zone DEFAULT now() NOT NULL,
    CONSTRAINT role_id_pk PRIMARY KEY (role_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.role OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
ALTER SEQUENCE public.role_role_id_seq OWNED BY public.role.role_id;



------------------
--- USERROLE-----
------------------
-- CREATE SEQUENCE public.userrole_userrole_id_seq
--     START WITH 100
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;

-- Create Table
CREATE TABLE public.userrole (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CREATE CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.user (user_id),
    CREATE CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES public.role (role_id)
);

-- Alter Table Owner to postgres
ALTER TABLE public.userrole OWNER TO postgres;

-- Alter Sequence Owned by the table primary key to make it more efficient
-- This means when student table is deleted, automatically delete this sequence.
-- ALTER SEQUENCE public.userrole_userrole_id_seq OWNED BY public.userrole.userrole_id;

-- CREATE TABLE public.authorities(
--     username character varying(50) NOT NULL,
--     authority character varying(50) NOT NULL,
--     constraint fk_authorities_users foreign key(username) references users(username)
-- );

------------------
-- new table --
------------------
-- Create Sequence
-- Create a sequence if we need to control start #
-- If no need to control, simple combination of "SERIAL PRIMARY KEY" will do

-- Create Table

-- Alter Table Ownder to postgres

-- Alter Sequence Owned by the table primary key to make it more efficient

-- END OF {New Table}--

---------------------
-- Create Crypto ----
---------------------

CREATE EXTENSION pgcrypto;


----------------------------------------------------
----------------- DEFINE RELATIONSHIP --------------
----------------------------------------------------
-- Define relationship after completing the table creation
-- Alter Table Add Foreign Key constraint, Reference, Delete Cascade
-- E.G. Deleting PK in Student will delete student_contact record associated with

-- PK student.membershipt_id in student_contact.membershipt_id as FK
ALTER TABLE ONLY public.student_contact
    ADD CONSTRAINT student_id_pk FOREIGN KEY (membership_id) REFERENCES public.student(membership_id) ON DELETE CASCADE;

-- guardian:contact 1:1  


-- payroll:timesheet 1:M

-- teacher:payroll 1:1

-- teacher:timesheet 1:M

-- teacher:report 1:M

-- student:report 1:M

-- program:lesson 1:M

-- term:program 1:M

-- student:term 1:M

-- term:invoice 1:1

-- invoice:pay_transaction 1:M



---------------------------------------------------
---------------- DEFINE FUNCTION ------------------
---------------------------------------------------

--
-- Name: last_updated(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION last_updated() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    NEW.last_update = CURRENT_TIMESTAMP;
    RETURN NEW;
END $$;


ALTER FUNCTION public.last_updated() OWNER TO postgres;


---------------------------------------------------
--------------- DEFINE TRIGGER --------------------
---------------------------------------------------
--
-- Name: last_updated; Type: TRIGGER; Schema: public; Owner: postgres
--

-- Add last_updated column to all tables
CREATE TRIGGER last_updated BEFORE UPDATE ON public.student FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.student_contact FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.guardian FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.teacher FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON payroll FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON timesheet FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON term FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON termprogram FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON program FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON lesson FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON report_card FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON invoice FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.pay_transaction FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.user FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.role FOR EACH ROW EXECUTE PROCEDURE last_updated();
CREATE TRIGGER last_updated BEFORE UPDATE ON public.userrole FOR EACH ROW EXECUTE PROCEDURE last_updated();


---------------------------------------------------
------------ ALTER TABLE --------------------------
---------------------------------------------------
-- Add additional columns to tables here
--ALTER TABLE lesson
--ALTER COLUMN teacher_id DROP NOT NULL;

