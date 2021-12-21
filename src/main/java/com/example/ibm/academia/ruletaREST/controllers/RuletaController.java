package com.example.ibm.academia.ruletaREST.controllers;

import com.example.ibm.academia.ruletaREST.entities.Apuesta;
import com.example.ibm.academia.ruletaREST.entities.Ruleta;
import com.example.ibm.academia.ruletaREST.services.ApuestaDAO;
import com.example.ibm.academia.ruletaREST.services.RuletaDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruletas")
@Api(value = "Ruletas", description = "REST API para Ruletas", tags = { "Ruletas" })
public class RuletaController {

    @Autowired
    private RuletaDAO ruletaDAO;

    @Autowired
    private ApuestaDAO apuestaDAO;

    Logger logger = LoggerFactory.getLogger(RuletaController.class);

    /**
     * endpoint creado con la finalidad de crear una ruleta
     * @param ruleta JSON body con la infromacion de la ruleta a crear
     * @param result lista de errores al validar la infromacion de JSON body
     * @return id de la ruleta creada
     */
    @PostMapping
    @ApiOperation("Crear uan ruleta")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Created"),
            @ApiResponse(code = 406, message = "Not Acceptable")}
    )
    public ResponseEntity<?>guardarRuleta(@Valid @RequestBody Ruleta ruleta, BindingResult result){

        Map<String, Object> respuesta = new HashMap<String, Object>();
        if (result.hasErrors())
        {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Campo: '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            respuesta.put("Lista Errores", listaErrores);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_ACCEPTABLE);
        }

        Ruleta ruletaCreada=ruletaDAO.guardar(ruleta);
        respuesta.put("id",ruletaCreada.getId().toString());
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);

    }

    /**
     * end point creado con la finalidad de dar apertura a una ruleta que ya ha sido creada
     * @param ruletaId id de la ruleta que se desea dar apertura
     * @return menesaje si la operacion fue exitosa o falló
     */
    @PostMapping("/apertura")
    @ApiOperation("Abrir una ruleta")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Created"),
            @ApiResponse(code = 406, message = "Not Acceptable")}
    )
    public ResponseEntity<?>abrirRuleta(@RequestParam(name = "id") Integer ruletaId){
            Optional<Ruleta> ruleta=null;
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
            ruleta=ruletaDAO.buscarPorID(ruletaId);
            ruletaDAO.aperturaRuleta(ruleta.get());
            respuesta.put("Operacion","Exitosa");
            return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.ACCEPTED);
        }catch (Exception e){
            logger.info(e.getMessage());
            respuesta.put("Operacion","Denegada");
        }
        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * end point creado para enviar y validar una apuesta
     * @param ruletaId el id de la ruleta donde se esta realizando la apuesta
     * @param dineroApuesta dinero que se esta apostando en el juego
     * @param tipoApuesta tipo de apuesta, si es por numero o color
     * @param apuesta la opcion elegida para apostar
     * @return la apuesta creada
     */
    @PostMapping("/apuesta")
    @ApiOperation("Apostar en una ruleta")
    @ApiResponses({
            @ApiResponse(code = 201,message = "Created"),
            @ApiResponse(code = 406, message = "Not Acceptable")}
    )public ResponseEntity<?>apostarEnRuleta(@RequestParam(name = "id") Integer ruletaId,@RequestParam(name = "dinero") Double dineroApuesta,@RequestParam(name = "tipoApuesta") Integer tipoApuesta,@RequestParam(name = "apuesta") String apuesta){
        Ruleta ruletaEncontrada=null;
        Apuesta apuestaRealizada=null;
        Map<String, Object> respuesta = new HashMap<String, Object>();
            try {
                ruletaEncontrada= ruletaDAO.buscarPorID(ruletaId).get();
                apuestaRealizada=apuestaDAO.validarApuesta(dineroApuesta,ruletaEncontrada,tipoApuesta,apuesta);

                return new ResponseEntity<Apuesta>(apuestaRealizada,HttpStatus.CREATED);
            }catch (Exception e){
                logger.info(e.getMessage());
                respuesta.put("error", "No se pudo realizar la apuesta");
            }

        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.NOT_ACCEPTABLE);

    }

    /**
     * en point creado con la finalidad de dar cierre a una ruleta y poder listar todas las apuesta que se realizaron ella
     * @param ruletaId el id de la ruleta que se va cerrra
     * @return listado de todas las apuesta realizadas en la ruleta selecionada
     */
    @PostMapping("/apuesta")
    @ApiOperation("Apostar en una ruleta")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")}
    )public ResponseEntity<?>cerrarRuleta(@RequestParam(name = "id") Integer ruletaId){
        Ruleta ruletaCerrada= null;
        Map<String, Object> respuesta = new HashMap<String, Object>();
        try {
             ruletaCerrada= ruletaDAO.buscarPorID(ruletaId).get();
             List<Apuesta> apuestas= (List<Apuesta>) apuestaDAO.obtenerApuestaDeRuleta(ruletaId);
             return new ResponseEntity<List<Apuesta>>(apuestas,HttpStatus.OK);
        }catch (Exception e){
            logger.info(e.getMessage());
            respuesta.put("Error","operacion no completada con exito");
        }
        return new ResponseEntity<Map<String, Object>>(respuesta,HttpStatus.BAD_REQUEST);
    }
}
