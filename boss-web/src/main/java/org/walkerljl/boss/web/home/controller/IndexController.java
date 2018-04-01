package org.walkerljl.boss.web.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.walkerljl.boss.support.mvc.controller.BaseIndexController;

/**
 * IndexController
 *
 * @author lijunlin
 */
@Controller
@RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.GET})
public class IndexController extends BaseIndexController {

}