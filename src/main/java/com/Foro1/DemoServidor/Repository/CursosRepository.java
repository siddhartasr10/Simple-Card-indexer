package com.Foro1.DemoServidor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;


@Repository
public class CursosRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public byte[] getImgById(Integer id) {
        String sql = ("SELECT img FROM cursos WHERE id = ?");
        byte[] img = jdbcTemplate.queryForObject(sql, byte[].class, new Object[]{id});
        return img;
    }

    // I query but don't return the img, only the rest of the columns
    public List<Curso> getCoursesLikeName(String name) {
        String sql = "SELECT id, name, description FROM cursos WHERE UPPER(name) LIKE UPPER(?) OR UPPER(description) LIKE UPPER(?)";
        name = ("%".concat(name)).concat("%");
        List<Curso> cursos = jdbcTemplate.query(sql, new Object[] {name, name}, new ResultSetExtractor <List<Curso>>() {
                @Override
                public List<Curso> extractData(ResultSet rs) throws SQLException {
                    List<Curso> cursos = new ArrayList<>();

                    while (rs.next()) {
                        Curso curso = new Curso(); curso.setId(rs.getInt("id")); curso.setName(rs.getString("name")); curso.setDescription(rs.getString("description"));
                        cursos.add(curso);
                    }
                    return cursos;
                }
            });
        return cursos;
    }

    public class Curso {
        Integer id; String name, description; byte[] img;
        public Curso(Integer id, String name, String description, byte[] img) {
            this.setId(id);
            this.setName(name);
            this.setDescription(description);
            this.setImg(img);
        }

        public Curso(Integer id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
        public Curso() {}

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

          public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public byte[] getImg() {
            return img;
        }

        public void setImg(byte[] img) {
            this.img = img;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        @Override
        public String toString() {
            return "Curso [id=" + id + ", name=" + name + ", description=" + description + ", img="
                    + Arrays.toString(img) + "]";
        }

    }
}
